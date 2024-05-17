package com.andreeatuslea.musicapp.controllers;

import com.andreeatuslea.musicapp.embeddableIds.LiedInTopId;
import com.andreeatuslea.musicapp.repositories.*;
import com.andreeatuslea.musicapp.services.DirtyReadService;
import com.andreeatuslea.musicapp.services.PreisService;
import com.andreeatuslea.musicapp.tables.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class Controller {

    @Autowired
    DirtyReadService dirtyReadService;

    @Autowired
    LiedRepo liedRepo;

    @Autowired
    AlbumRepo albumRepo;

    @Autowired
    TopLiederRepo topLiederRepo;

    @Autowired
    LiedInTopRepo liedInTopRepo;

    @Autowired
    SingersRepo singersRepo;
    @Autowired
    PreisRepo preisRepo;

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @GetMapping("/")
    public ModelAndView index()
    {
        return new ModelAndView("index");
    }

    @GetMapping("/album/{albumId}")
    public ModelAndView album(@PathVariable Integer albumId) {
        return new ModelAndView("album");
    }

    @GetMapping("/albums")
    public ModelAndView albums() {
        return new ModelAndView("albums");
    }

    @GetMapping("/artists")
    public ModelAndView artists() {
        return new ModelAndView("artists");
    }

    @GetMapping ("/getAlbum/{albumId}")
    public Album getAlbum(@PathVariable Integer albumId, HttpServletResponse response) {
        Album foundAlbum = albumRepo.findAlbumByAlbumId(albumId);

        if (foundAlbum == null)
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        return foundAlbum;
    }

    @GetMapping ("/getAlbums")
    public List<Album> getAlbums(HttpServletResponse response) {
        List<Album> albums = new ArrayList<>();

        albumRepo.findAll().forEach(albums::add);

        return albums;
    }

    @GetMapping ("/getSinger/{singerId}")
    public Singers getSinger(@PathVariable Integer singerId, HttpServletResponse response) {
        Singers singer = singersRepo.getSingersBySingerId(singerId);

        if (singer == null)
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        return singer;
    }

    @GetMapping("/getSongs/{albumId}")
    public List<Lied> getSongs(@PathVariable Integer albumId, HttpServletResponse response) {
        Album album = albumRepo.findAlbumByAlbumId(albumId);

        if(album==null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return liedRepo.findAllByAlbumId(albumId);

    }

    @GetMapping("/getSingers")
    public List<Singers> getSingers()
    {
        List<Singers> singers = new ArrayList<>();

        singersRepo.findAll().forEach(singers::add);

        return singers;
    }

    @GetMapping("/getPosition/{leaderboardId}/{songId}")
    public Integer getPosition(@PathVariable Integer leaderboardId, @PathVariable Integer songId)
    {
        LiedInTop liedInTop = liedInTopRepo.findLiedInTopByLiedInTopId_LiedIdAndLiedInTopId_LiedId(leaderboardId,songId);
        return liedInTop.getPosition();
    }

    @PostMapping("/purchase/{albumId}")
    public void purchase(@PathVariable Integer albumId, HttpServletResponse response)
    {
        Album foundAlbum = albumRepo.findAlbumByAlbumId(albumId);

        if (foundAlbum == null)
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        foundAlbum.setNrCopiesSold(foundAlbum.getNrCopiesSold()+1);
        albumRepo.save(foundAlbum);
    }

    @GetMapping("/concurrencyIssues")
    public ModelAndView getConcurrencyIssue(HttpServletResponse response)
    {
        return new ModelAndView("concurrency_issue");
    }

    @PostMapping("/setOnLeaderboard/{leaderboardId}/{songId}/{position}")
    public void setOnLeaderboard(@PathVariable Integer leaderboardId, @PathVariable Integer songId, @PathVariable Integer position)
    {
        LiedInTop liedInTop = new LiedInTop();
        liedInTop.setLiedInTopId(new LiedInTopId());
        liedInTop.getLiedInTopId().setLiedId(songId);
        liedInTop.getLiedInTopId().setTopId(leaderboardId);
        liedInTop.setPosition(position);

        liedInTopRepo.save(liedInTop);
    }


    @PostMapping("/lostUpdate/{albumId}")
    public void lostUpdate(@PathVariable Integer albumId, HttpServletResponse response) throws InterruptedException {
        Thread bigThread = new Thread(new Runnable() {

            Thread smallThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("small thread start");
                    purchase(albumId, response);
                }
            });

            @Override
            public void run() {
                System.out.println("big thread start");
                smallThread.start();
                purchase(albumId, response);
            }
        });

        bigThread.start();
    }

    @PostMapping("/dirtyWrite/{songId}")
    public void dirtyWrite(@PathVariable Integer songId, HttpServletResponse response) throws InterruptedException {
        Thread bigThread = new Thread(new Runnable() {

            Thread smallThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("small thread start");
                    setOnLeaderboard(0, songId, 1);
                }
            });

            @Override
            public void run() {
                System.out.println("big thread start");
                smallThread.start();
                setOnLeaderboard(0, songId, 100);
            }
        });

        bigThread.start();
    }

    @GetMapping("/unrepeatableRead/{singerId}")
    public Object unrepeatableRead(@PathVariable Integer singerId, HttpServletResponse response) throws InterruptedException {

        class MyRunnable implements Runnable
        {
            public String[] value = new String[2];

            Thread smallThread = new Thread(() -> {
                System.out.println("small thread start");
                Singers singer = singersRepo.getSingersBySingerId(singerId);
                singer.setNickname(singer.getNickname() + singer.getNickname().length());
                singersRepo.save(singer);
            });

            @Override
            public void run() {
                System.out.println("big thread start");
                smallThread.start();
                value[0] = singersRepo.getSingersBySingerId(singerId).getNickname();
                try {
                    smallThread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                value[1] = singersRepo.getSingersBySingerId(singerId).getNickname();
            }
        }

        MyRunnable runnable = new MyRunnable();

        Thread bigThread = new Thread(runnable);

        bigThread.start();

        bigThread.join();

        return runnable.value;
    }

    @GetMapping("/phantomRead")
    public Object phantomRead(HttpServletResponse response) throws InterruptedException {

        class MyRunnable implements Runnable
        {
            public Integer[] value = new Integer[2];

            Thread smallThread = new Thread(() -> {
                System.out.println("small thread start");
                Lied lied = new Lied();
                lied.setName("Song");
                lied.setDauer(130);
                liedRepo.save(lied);
                Lied lied2 = new Lied();
                lied2.setName("Song2");
                lied2.setDauer(130);
                liedRepo.save(lied2);
                Lied lied3 = new Lied();
                lied3.setName("Song3");
                lied3.setDauer(130);
                liedRepo.save(lied3);
            });

            @Override
            public void run() {
                System.out.println("big thread start");
                value[0] = liedRepo.findLiedsByDauerIsGreaterThan(120).size();
                smallThread.start();
                try {
                    smallThread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                value[1] = liedRepo.findLiedsByDauerIsGreaterThan(120).size();
            }
        }

        MyRunnable runnable = new MyRunnable();

        Thread bigThread = new Thread(runnable);

        bigThread.start();

        bigThread.join();

        return runnable.value;
    }

    @GetMapping("/dirtyRead")
    public Object dirtyRead() throws InterruptedException {
        boolean[] value = new boolean[2];

        // Start transaction1
        value = dirtyReadService.transaction1(preisRepo);

        return value;
    }

}

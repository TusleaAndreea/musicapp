using CSDB.tables;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSDB.classes
{
    internal class AlbumPage : Page
    {
        Album album;
        bool purchased;

        public AlbumPage(Album album, bool purchased)
        {
            this.album = album;
            this.purchased = purchased;
        }

        public override void print()
        {
            Singers singer = new Repo<Singers>().getQueryResults($"SELECT * FROM Singers WHERE singer_id={album.singer_id}").First();
            Console.WriteLine(album.name);
            Console.WriteLine($"by {singer.nickname}");
            List<Lied> songs = new Repo<Lied>().findAll();
            foreach( Lied lied in songs )
            {
                if (lied.album_id == album.album_id)
                    Console.WriteLine(lied.name);
            }


            Console.WriteLine("0. Back");
            Console.WriteLine("1. Purchase");
        }

        public override Page afterInput(string input)
        {
            if (input == "0")
                return new AlbumsPage();
            else if (input == "1")
            {
                new Repo<Lied>().execUpdate($"UPDATE Album SET nr_copies_sold={album.nr_copies_sold + 1} WHERE album_id={album.album_id}");
                return new AlbumPage(album, true);
            }
            return new AlbumPage(album, false);
        }


    }
}

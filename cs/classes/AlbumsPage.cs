using CSDB.tables;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSDB.classes
{
    public class AlbumsPage : Page
    {

        List<Album> albums = new Repo<Album>().findAll();

        public override void print()
        {
            Console.WriteLine("0. Back");
            foreach (Album album in albums)
            {
                Console.WriteLine($"{album.album_id}. {album.name}");
            }
        }

        public override Page afterInput(string input)
        {
            if (input == "0")
                return new MainPage();
            return new AlbumPage(albums[int.Parse(input)-1], false);
        }

    }
}

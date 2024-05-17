using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSDB.classes
{
    internal class MainPage : Page
    {
        public override void print()
        {
            Console.WriteLine("1. View Albums");
            Console.WriteLine("2. View Singers");
        }

        public override Page afterInput(string input)
        {
            if (input == "1")
                return new AlbumsPage();
            if (input == "2")
                return new SingersPage();

            return this;
        }
    }
}

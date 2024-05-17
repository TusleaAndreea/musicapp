using CSDB.tables;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSDB.classes
{
    public class SingersPage : Page
    {

        public override void print()
        {
            List<Singers> singers = new Repo<Singers>().findAll();
            foreach (Singers s in singers)
            {
                Console.WriteLine(s.nickname);
            }
            Console.WriteLine("0. Back");
        }
        public override Page afterInput(string input)
        {
            return new MainPage();
        }
    }
}

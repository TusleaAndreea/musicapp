using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace CSDB.tables
{
    public class Album
    {
        public int album_id;
        public string name;
        public int nr_copies_sold;
        public int singer_id;
        public DateTime veroeffentlichung;

        public Album(int album_id, string name, int nr_copies_sold, int singer_id, DateTime veroeffentlichung)
        {
            this.album_id = album_id;
            this.name = name;
            this.nr_copies_sold = nr_copies_sold;
            this.singer_id  = singer_id;
            this.veroeffentlichung = veroeffentlichung;
        }
    }
}

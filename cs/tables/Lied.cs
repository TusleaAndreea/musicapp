using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSDB.tables
{
    public class Lied
    {

        public int lied_id;
        public int album_id;
        public int dauer;
        public string name;

        public Lied(int lied_id, int album_id, int dauer, string name)
        {
            this.lied_id = lied_id;
            this.album_id = album_id;
            this.dauer = dauer;
            this.name = name;
        }

    }
}

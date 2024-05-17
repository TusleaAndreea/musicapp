using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace CSDB.tables
{
    public class Singers
    {
        public int singer_id;
        public DateTime geburts_datum;
        public int label_id;
        public string name;
        public string nickname;
        public string vorname;

        public Singers(int singer_id,
                        DateTime geburts_datum,
                        int label_id,
                        string name,
                        string nickname,
                        string vorname)
        {
            this.singer_id = singer_id;
            this.geburts_datum = geburts_datum;
            this.label_id = label_id;
            this.name = name;
            this.nickname = nickname;
            this.vorname = vorname;
        }
    }
}

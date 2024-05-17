using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSDB.classes
{
    public abstract class Page
    {
        public abstract Page afterInput(string input);
        public abstract void print();
    }
}

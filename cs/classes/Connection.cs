using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSDB.classes
{
    public class Connection
    {

        private static Connection instance = new Connection();

        MySqlConnection connection = new MySqlConnection("server=127.0.0.1;uid=root;pwd=pisica65;database=musicapp");

        Connection()
        {
            connection.Open();
        }

        public static Connection getInstance()
        {
            return instance;
        }

        public static MySqlConnection getConnection()
        {
            return getInstance().connection;
        }

    }
}

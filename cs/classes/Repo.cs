using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSDB.classes
{
    public class Repo<T>
    {

        public List<T> findAll()
        {
            return getQueryResults($"SELECT * FROM {typeof(T).Name}");
        }

        public List<T> getQueryResults(string query)
        {
            MySqlCommand mySqlCommand = new MySqlCommand(query);
            mySqlCommand.Connection = Connection.getConnection();
            MySqlDataReader mySqlDataReader = mySqlCommand.ExecuteReader();

            List<T> result = new List<T>();

            while (mySqlDataReader.Read())
            {
                List<object> parameters = new List<object>();

                for (int i = 0; i < mySqlDataReader.FieldCount; i++)
                    parameters.Add(mySqlDataReader.GetValue(i));

                try
                {
                    
                    T a = (T)Activator.CreateInstance(typeof(T), parameters.ToArray());
                    result.Add(a);
                }
                catch(Exception e)
                {

                }
            }

            mySqlDataReader.Close();

            return result;
        }

        public int execUpdate(string update)
        {
            MySqlCommand mySqlCommand = new MySqlCommand(update);
            mySqlCommand.Connection = Connection.getConnection();
            return mySqlCommand.ExecuteNonQuery();
        }

    }
}

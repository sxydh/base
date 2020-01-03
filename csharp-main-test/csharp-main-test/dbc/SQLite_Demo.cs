using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace csharp_main_test.jdbc
{
    public class SQLite_Demo
    {
        private SQLiteCommand cmd;
        private SQLiteConnection cn;


        /*path: full database path, include file name*/
        public SQLite_Demo(string path)
        {
            cmd = new SQLiteCommand();
            cn = new SQLiteConnection("data source=" + path);
            if (cn.State != System.Data.ConnectionState.Open)
            {
                cn.Open();
            }
            cmd.Connection = cn;
        }

        /*sql format must conform to the specification*/
        public SQLiteDataReader ExecuteQuery(string sql)
        {
            cmd.CommandText = sql;
            SQLiteDataReader reader = cmd.ExecuteReader();
            return reader;
        }

        /*sql format must conform to the specification*/
        public void ExecuteNonQuery(string sql)
        {
            cmd.CommandText = sql;
            cmd.ExecuteNonQuery();
        }
    }
}

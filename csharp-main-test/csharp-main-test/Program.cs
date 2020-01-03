using csharp_main_test.io;
using csharp_main_test.jdbc;
using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace csharp_main_test
{
    class Program
    {
        /*test entrance*/
        static void Main(string[] args)
        {
            /*string path = IO_Demo.GetEntryPath()();
            Console.WriteLine(path);*/

            /*
            string databasePath = IO_Demo.GetEntryPath();
            databasePath += @"jdbc\main.db";
            SQLite_Demo sQLite_Demo = new SQLite_Demo(databasePath);
            sQLite_Demo.ExecuteNonQuery(@"CREATE TABLE IF NOT EXISTS test (id int primary key not null,data char(255))");
            Console.WriteLine("create table successfully");
            sQLite_Demo.ExecuteNonQuery(@"INSERT INTO test (id,data) VALUES((SELECT COALESCE(MAX(id),0)+1 FROM test),'data one')");
            Console.WriteLine("insert data successfully");
            SQLiteDataReader sQLiteDataReader = sQLite_Demo.ExecuteQuery(@"SELECT * FROM test");
            string result = "";
            while (sQLiteDataReader.Read())
            {
                result = sQLiteDataReader.GetInt32(0).ToString();
                result += "- - -";
                result += sQLiteDataReader.GetString(1);
            }
            Console.WriteLine(result);*/

            Console.ReadKey();
        }
    }
}

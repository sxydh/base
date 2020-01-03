using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace csharp_main_test.io
{
    public class IO_Demo
    {
        /*get the path of the entry program*/
        public static string GetEntryPath()
        {
            string path = new DirectoryInfo("../../").FullName;
            return path;
        }
    }
}

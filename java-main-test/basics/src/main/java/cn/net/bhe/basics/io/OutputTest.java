package cn.net.bhe.basics.io;

import static cn.net.bhe.utils.main.PrintUtils.*;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutputTest {

    static Logger LOGGER = LoggerFactory.getLogger(OutputTest.class);

    private static String path = "C:/Users/Administrator/Desktop/10011.txt";
    
    public static <T extends Closeable> void close(T closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                pln(e.getLocalizedMessage());
            }
        }
    }

    /*
     * ****************************************************************
     * Writer start
     * ****************************************************************
     */
    public static void 
    bufferedWriter() 
    // main(String[] args)
    {
        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(path);
            buffer = new BufferedWriter(writer);
            buffer.write("Welcome to javaTpoint.");
            pln("Success");
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(buffer);
            close(writer);
        }
    }

    public static void 
    outputStreamWriter() 
    // main(String[] args)
    {
        OutputStream outputStream = null;
        Writer outputStreamWriter = null;
        try {
            outputStream = new FileOutputStream(path);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write("Hello World");
            pln("Success");
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(outputStreamWriter);
        }
    }

    public static void 
    fileWriter() 
    // main(String[] args)
    {
        FileWriter fw = null;
        try {
            fw = new FileWriter(path);
            fw.write("Welcome to javaTpoint.");
            pln("Success");
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(fw);
        }
    }

    public static void 
    printWriter() 
    // main(String[] args)
    {
        // Data to write on Console using PrintWriter  
        PrintWriter writer = new PrintWriter(System.out);
        writer.write("Javatpoint provides tutorials of all technology.");
        writer.flush();
        writer.close();
        // Data to write in File using PrintWriter       
        PrintWriter writer1 = null;
        try {
            writer1 = new PrintWriter(new File(path));
            writer1.write("Like Java, Spring, Hibernate, Android, PHP etc.");
            writer1.flush();
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(writer1);
        }
    }

    public static void 
    stringWriter() 
    // main(String[] args)
    {
        char[] ary = new char[10];
        StringWriter writer = new StringWriter();
        FileInputStream input = null;
        BufferedReader buffer = null;
        try {
            input = new FileInputStream(path);
            buffer = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            int x;
            while ((x = buffer.read(ary)) != -1) {
                writer.write(ary, 0, x);
            }
            pln(writer.toString());
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(buffer);
            close(input);
        }
    }

    public static void 
    pipedWriter() 
    // main(String[] args)
    {
        final PipedReader pipedReader = new PipedReader();
        final PipedWriter pipedWriter = new PipedWriter();

        // Connect pipe
        try {
            pipedReader.connect(pipedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Writing data to pipe
        Thread writerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 65; i <= 70; i++) {
                        pipedWriter.write((char) i);
                        Thread.sleep(500);
                    }
                    pipedWriter.close();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Reading data from pipe
        Thread readerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int i;
                    while ((i = pipedReader.read()) != -1) {
                        System.out.println((char) i);
                        Thread.sleep(5000);
                    }
                    pipedReader.close();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start thread
        writerThread.start();
        readerThread.start();
    }

    public static void 
    charArrayWriter()
    // main(String[] args)
    {
        // Initailizing the character array 
        char[] geek = { 'G', 'E', 'E', 'K', 'S' };
        String geek_str;

        // Initailizing the CharArrayWriter 
        CharArrayWriter char_array1 = new CharArrayWriter();
        CharArrayWriter char_array2 = new CharArrayWriter();
        CharArrayWriter char_array3 = new CharArrayWriter();

        for (int c = 72; c < 77; c++) {
            // Use of write(int char) 
            // Writer int value to the Writer 
            char_array1.write(c);
        }

        // Use of toString() : returning Buffer content as String 
        geek_str = char_array1.toString();
        pln("Using write(int char) : " + geek_str);

        // Use of write(String str, int offset, int maxlen) 
        // writes some part of the string to the Writer. 
        char_array2.write(geek_str, 2, 3);

        pln("write(str, offset, maxlen) : " + char_array2.toString());

        // Use of write(char[] carray, int offset, int maxlen) 
        // writes some part of the Char[] geek to the Writer 
        char_array3.write(geek, 2, 3);
        pln("write(carray, offset, maxlen) : " + char_array3.toString());

        // get buffered content as string 
        @SuppressWarnings("unused")
        String str = char_array3.toString();

        // Use of writeTo(Writer out_stream) 
        try {
            char_array3.writeTo(char_array1);
        } catch (IOException e) {
            pln(e.getLocalizedMessage());
        }

        pln("\nchar_array3 to char_array1 : " + char_array1.toString());

        // Use of size() method 
        pln("\nSize of char_array1 : " + char_array1.size());
        pln("Size of char_array1 : " + char_array2.size());
        pln("Size of char_array1 : " + char_array3.size());
    }

    public static void 
    filterWriter() 
    // main(String[] args)
    {
        FileWriter fw = null;
        FilterWriter filterWriter = null;
        FileReader fr = null;
        BufferedReader bufferedReader = null;
        try {
            fw = new FileWriter(path);
            filterWriter = new FilterWriter(fw) {
                public void write(String str) throws IOException {
                    super.write(str.toLowerCase());
                }
            };
            filterWriter.write("I LOVE MY COUNTRY");
            filterWriter.close();
            fr = new FileReader(path);
            bufferedReader = new BufferedReader(fr);
            int k;
            while ((k = bufferedReader.read()) != -1) {
                System.out.print((char) k);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(filterWriter);
            close(fw);
            close(bufferedReader);
            close(fr);
        }
    }
    /*
     * ****************************************************************
     * Writer end
     * ****************************************************************
     */

    /*
     * ****************************************************************
     * OutputStream start
     * ****************************************************************
     */
    public static void 
    fileOutputStream() 
    // main(String[] args)
    {
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(path);
            fout.write(65);
            pln("Success");
        } catch (Exception e) {
            pln(e);
        } finally {
            close(fout);
        }
    }

    public static void 
    filterOutputStream() 
    // main(String[] args)
    {
        FileOutputStream file = null;
        FilterOutputStream filter = null;
        try {
            file = new FileOutputStream(path);
            filter = new FilterOutputStream(file);
            String s = "Welcome to javaTpoint.";
            byte b[] = s.getBytes();
            filter.write(b);
            filter.flush();
            pln("Success");
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(filter);
            close(file);
        }
    }

    public static void 
    bufferedOutputStream() 
    // main(String[] args)
    {
        FileOutputStream fout = null;
        BufferedOutputStream bout = null;
        try {
            fout = new FileOutputStream(path);
            bout = new BufferedOutputStream(fout);
            String s = "Welcome to javaTpoint.";
            byte b[] = s.getBytes();
            bout.write(b);
            bout.flush();
            pln("Success");
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(bout);
            close(fout);
        }
    }

    public static void 
    dataOutputStream() 
    // main(String[] args)
    {
        FileOutputStream file = null;
        DataOutputStream data = null;
        try {
            file = new FileOutputStream(path);
            data = new DataOutputStream(file);
            data.writeInt(65);
            data.flush();
            pln("Success");
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(data);
            close(file);
        }
    }

    public static void 
    printStream() 
    // main(String[] args)
    {
        FileOutputStream fout = null;
        PrintStream pout = null;
        try {
            fout = new FileOutputStream(path);
            pout = new PrintStream(fout);
            pout.println(1900);
            pout.println("Hello Java");
            pout.println("Welcome to Java");
            pln("Success");
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(pout);
            close(fout);
        }
    }

    public static void 
    objectOutputStream()
    // main(String[] args)
    {
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            Map<String, Object> obj = new LinkedHashMap<>();
            obj.put("name", "Jakob Jenkov");
            obj.put("age", 40);
            objectOutputStream.writeObject(obj);

            objectInputStream = new ObjectInputStream(new FileInputStream(path));
            @SuppressWarnings("unchecked")
            Map<String, Object> read = (Map<String, Object>) objectInputStream.readObject();
            pln(read.get("name"));
            pln(read.get("age"));
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(objectInputStream);
            close(objectOutputStream);
        }
    }

    public static void 
    pipedOutputStream()
    // main(String[] args)
    {
        @SuppressWarnings("resource")
        final PipedOutputStream pout = new PipedOutputStream();
        final PipedInputStream pin = new PipedInputStream();

        try {
            pout.connect(pin); // connecting the streams  
        } catch (IOException e) {
            pln(e.getLocalizedMessage());
        }
        // creating one thread t1 which writes the data  
        Thread t1 = new Thread() {
            public void run() {
                for (int i = 65; i <= 90; i++) {
                    try {
                        pout.write(i);
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }
            }
        };
        // creating another thread t2 which reads the data  
        Thread t2 = new Thread() {
            public void run() {
                try {
                    for (int i = 65; i <= 90; i++)
                        pln(pin.read());
                } catch (Exception e) {
                }
            }
        };
        // starting both threads  
        t1.start();
        t2.start();
    }

    public static void 
    // byteArrayOutputStream() 
    main(String[] args)
    {
        FileOutputStream fout1 = null;
        FileOutputStream fout2 = null;
        ByteArrayOutputStream bout = null;
        try {
            fout1 = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\f1.txt");
            fout2 = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\f2.txt");
            bout = new ByteArrayOutputStream();
            bout.write(65);
            bout.writeTo(fout1);
            bout.writeTo(fout2);
            bout.flush();
            pln("Success");
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(bout); // has no effect  
            close(fout1);
            close(fout2);
        }
    }
    /*
     * ****************************************************************
     * OutputStream end
     * ****************************************************************
     */
    
}

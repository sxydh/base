package cn.net.bhe.basics.io;

import static cn.net.bhe.utils.main.PrintUtils.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilterInputStream;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PushbackInputStream;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.io.StringReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputTest {

    static Logger LOGGER = LoggerFactory.getLogger(InputTest.class);

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
     * Reader start
     * ****************************************************************
     */
    public static void
    bufferedReader()
    // main(String[] args)
    {
        StringBuilder result = new StringBuilder();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            String temp;
            while ((temp = br.readLine()) != null) {
                result.append(temp + "\n");
            }
            pln(result.toString());
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(br);
            close(fr);
        }
    }

    public static void 
    inputStreamReader()
    // main(String[] args)
    {
        StringBuilder result = new StringBuilder();
        InputStream inputStream = null;
        Reader inputStreamReader = null;
        try {
            inputStream = new FileInputStream(path);
            inputStreamReader = new InputStreamReader(inputStream);
            int data;
            while ((data = inputStreamReader.read()) != -1) {
                result.append((char) data);
            }
            pln(result);
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(inputStreamReader);
            close(inputStream);
        }
    }

    public static void
    fileReader()
    // main(String[] args)
    {
        StringBuilder result = new StringBuilder();
        FileReader fr = null;
        try {
            fr = new FileReader(path);
            int i;
            while ((i = fr.read()) != -1) {
                result.append((char) i);
            }
            pln(result);
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(fr);
        }
    }

    public static void 
    stringReader() 
    // main(String[] args)
    {
        String input = "Input String... ";
        StringReader stringReader = new StringReader(input);
        try {
            int data;
            String result = "";
            while ((data = stringReader.read()) != -1) {
                result += (char) data;
            }
            pln(result);
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        }
    }

    public static void 
    pipeReader() 
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
    filterReader() 
    // main(String[] args)
    {
        StringBuilder result = new StringBuilder();
        Reader reader = null;
        FilterReader fr = null;
        try {
            reader = new FileReader(path);
            fr = new FilterReader(reader) {
                @Override
                public int read() throws IOException {
                    int x = super.read();
                    if ((char) x == ' ')
                        return ('>');
                    else
                        return x;
                }
            };
            int i;
            while ((i = fr.read()) != -1) {
                result.append((char) i);
            }
            pln(result);
        } catch (Exception e) {
            pln(e.getMessage());
        } finally {
            close(fr);
            close(reader);
        }
    }

    public static void 
    pushbackReader() 
    // main(String[] args)
    {
        char ary[] = { '1', '-', '-', '2', '-', '3', '4', '-', '-', '-', '5', '6' };
        CharArrayReader reader = new CharArrayReader(ary);
        PushbackReader push = new PushbackReader(reader);
        int i;
        try {
            while ((i = push.read()) != -1) {
                if (i == '-') {
                    int j;
                    if ((j = push.read()) == '-') {
                        p("#*");
                    } else {
                        push.unread(j); // push back single character
                        p((char) i);
                    }
                } else {
                    p((char) i);
                }
            }
        } catch (Exception e) {
            pln(e.getMessage());
        }
    }
    /*
     * ****************************************************************
     * Reader end
     * ****************************************************************
     */

    /*
     * ****************************************************************
     * InputStream start
     * ****************************************************************
     */
    public static void 
    fileInputStream()
    // main(String[] args)
    {
        StringBuilder result = new StringBuilder();
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(path);
            int i;
            while ((i = fin.read()) != -1) {
                result.append((char) i);
            }
            pln(result);
        } catch (Exception e) {
            pln(e);
        } finally {
            close(fin);
        }
    }

    public static void 
    filterInputStream() 
    // main(String[] args) 
    {
        FileInputStream file = null;
        FilterInputStream filter = null;
        try {
            file = new FileInputStream(path);
            filter = new BufferedInputStream(file);
            int k = 0;
            while ((k = filter.read()) != -1) {
                p((char) k);
            }
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(filter);
            close(file);
        }
    }

    public static void 
    bufferedInputStream() 
    // main(String[] args) 
    {
        FileInputStream fin = null;
        BufferedInputStream bin = null;
        try {
            fin = new FileInputStream(path);
            bin = new BufferedInputStream(fin);
            int i;
            while ((i = bin.read()) != -1) {
                p((char) i);
            }
        } catch (Exception e) {
            pln(e);
        } finally {
            close(bin);
            close(fin);
        }
    }

    public static void 
    dataInputStream() 
    // main(String[] args) 
    {
        InputStream input = null;
        DataInputStream inst = null;
        try {
            input = new FileInputStream(path);
            inst = new DataInputStream(input);
            int count = input.available();
            byte[] ary = new byte[count];
            inst.read(ary);
            for (byte bt : ary) {
                char k = (char) bt;
                p(k);
            }
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(inst);
            close(input);
        }
    }

    public static void 
    pushbackInputStream() 
    // main(String[] args) 
    {
        String srg = "1##2#34###12";
        byte ary[] = srg.getBytes();
        ByteArrayInputStream array = new ByteArrayInputStream(ary);
        PushbackInputStream push = new PushbackInputStream(array);
        int i;
        try {
            while ((i = push.read()) != -1) {
                if (i == '#') {
                    int j;
                    if ((j = push.read()) == '#') {
                        p("**");
                    } else {
                        push.unread(j);
                        p((char) i);
                    }
                } else {
                    p((char) i);
                }
            }
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        }
    }

    public static void 
    objectInputStream() 
    // main(String[] args) 
    {
        String str = "Hello";
        byte[] barray = { 'j', 'a', 'v', 'a', 'T', 'p', 'o', 'i', 'n', 't' };
        ObjectOutputStream objoutstream = null;
        ObjectInputStream objinstream = null;
        try {
            // create a new file with an ObjectOutputStream
            FileOutputStream outstream = new FileOutputStream(path); // This file must be present in project directory
            objoutstream = new ObjectOutputStream(outstream);

            // writing objects str and barray to objectoutputstream
            objoutstream.writeObject(str);
            objoutstream.writeObject(barray);

            // create an ObjectInputStream for the file we created before
            objinstream = new ObjectInputStream(new FileInputStream(path));

            // read and print an object and cast it as string
            int read = objinstream.read();
            if (read == -1)
                pln("End of the stream is reached");
            else
                pln("No of bytes read is :" + read);
            while (true) {
                Object obj = objinstream.readObject();
                pln(obj);
            }
        } catch (Exception ex) {
            pln(ex.getLocalizedMessage()); // a solution to break the loop
        } finally {
            close(objoutstream);
            close(objinstream);
        }
    }

    public static void 
    pipedInputStream() 
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
    sequenceInputStream() 
    // main(String[] args) 
    {
        FileInputStream input1 = null;
        FileInputStream input2 = null;
        SequenceInputStream inst = null;
        try {
            input1 = new FileInputStream(path);
            input2 = new FileInputStream(path);
            inst = new SequenceInputStream(input1, input2);
            int j;
            while ((j = inst.read()) != -1) {
                p((char) j);
            }
        } catch (Exception e) {
            pln(e.getLocalizedMessage());
        } finally {
            close(inst);
            close(input1);
            close(input2);
        }
    }

    public static void 
    // byteArrayInputStream() 
    main(String[] args) 
    {
        byte[] buf = { 35, 36, 37, 38 };
        // Create the new byte array input stream  
        ByteArrayInputStream byt = new ByteArrayInputStream(buf);
        int k = 0;
        while ((k = byt.read()) != -1) {
            // Conversion of a byte into character  
            char ch = (char) k;
            pln("ASCII value of Character is:" + k + "; Special character is: " + ch);
        }
    }
    /*
     * ****************************************************************
     * InputStream end
     * ****************************************************************
     */

}

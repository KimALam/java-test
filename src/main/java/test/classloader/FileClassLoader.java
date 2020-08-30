package test.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

class FileClassLoader extends ClassLoader {
    private String root;

    public FileClassLoader(String rootDir) throws FileNotFoundException {
        super(FileClassLoader.class.getClassLoader());

        File f = new File(rootDir);
        if (f.isDirectory()) {
            this.root = rootDir;
        } else {
            throw new FileNotFoundException(rootDir + " isn't a directory");
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String path = root + File.separatorChar + name.replace('.', File.separatorChar) + ".class";
            FileInputStream in = new FileInputStream(path);
            byte[] classBytes = new byte[in.available()];
            in.read(classBytes);
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }

    @Override
    protected URL findResource(String name) {
        File resource = new File(root + File.separatorChar + name);
        if (resource.exists()) {
            try {
                return resource.toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

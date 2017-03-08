package io.unicorn;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;

/**
 * Created by Abderrazak BOUADMA
 * on 08/03/2017.
 */
public class BufferedServletInputStream extends ServletInputStream {

    private ByteArrayInputStream bais;

    public BufferedServletInputStream(ByteArrayInputStream bais) {
        this.bais = bais;
    }

    @Override
    public int available() {
        return this.bais.available();
    }

    @Override
    public int read() {
        return this.bais.read();
    }

    @Override
    public int read(byte[] buf, int off, int len) {
        return this.bais.read(buf, off, len);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener listener) {

    }
}

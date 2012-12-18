/*
 * The MIT License
 *
 * Copyright 2012 Praqma.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.praqma.jenkins.memorymap.parser;

import hudson.FilePath;
import hudson.remoting.VirtualChannel;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.praqma.jenkins.memorymap.result.MemoryMapConfigMemory;

/**
 *
 * @author Praqma
 */
public class MemoryMapConfigFileParserDelegate implements FilePath.FileCallable<MemoryMapConfigMemory>  {

    private static final Logger log = Logger.getLogger(MemoryMapMapParserDelegate.class.getName());
    private AbstractMemoryMapParser parser;
    
    public MemoryMapConfigFileParserDelegate() { }
    
    public MemoryMapConfigFileParserDelegate(AbstractMemoryMapParser parser) { 
        this.parser = parser;
    }
    
    @Override
    public MemoryMapConfigMemory invoke(File f, VirtualChannel channel) throws IOException, InterruptedException {
        MemoryMapConfigMemory mmcm = null;
        
        try {
            mmcm = parser.parseConfigFile(f);
        } catch (IOException ex) {
            log.logp(Level.WARNING, "invoke", MemoryMapConfigFileParserDelegate.class.getName(), "invoke caught file not found exception", ex);
            throw new IOException(ex.getMessage());
        }
        
        return mmcm;
    }

    /**
     * @return the parser
     */
    public AbstractMemoryMapParser getParser() {
        return parser;
    }

    /**
     * @param parser the parser to set
     */
    public void setParser(AbstractMemoryMapParser parser) {
        this.parser = parser;
    }
    
}

package org.elasticsearch.index.analysis.normalizer;

import org.apache.lucene.analysis.charfilter.BaseCharFilter;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.Normalizer;

public class NFDConvertFilter extends BaseCharFilter {

    private Reader transformedInput;

    public NFDConvertFilter(Reader tokenStream) {
        super(tokenStream);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        if (transformedInput == null) {
            convertNFD();
        }

        return transformedInput.read(cbuf, off, len);
    }

    private void convertNFD() throws IOException {
        StringBuilder buffered = new StringBuilder();
        char [] temp = new char [1024];
        for (int cnt = input.read(temp); cnt > 0; cnt = input.read(temp)) {
            buffered.append(temp, 0, cnt);
        }
        transformedInput = new StringReader(Normalizer.normalize(buffered.toString(), Normalizer.Form.NFD));
    }

}

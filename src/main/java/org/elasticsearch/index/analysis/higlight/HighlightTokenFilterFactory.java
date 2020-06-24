package org.elasticsearch.index.analysis.higlight;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

public class HighlightTokenFilterFactory extends AbstractTokenFilterFactory {

    public HighlightTokenFilterFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, name, settings);
    }
    

    @Override
    public TokenStream create(TokenStream stream) {
        return new HighlightTokenFilter(stream);
    }

    
    
}

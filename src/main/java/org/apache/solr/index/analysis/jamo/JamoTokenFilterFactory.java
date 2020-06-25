package org.apache.solr.index.analysis.jamo;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;
import org.elasticsearch.index.analysis.jamo.JamoTokenFilter;

import java.util.Map;

public class JamoTokenFilterFactory extends TokenFilterFactory {

    
    public JamoTokenFilterFactory(Map<String, String> args) {
        super(args);
    }
    

    @Override
    public TokenStream create(TokenStream stream) {
        return new JamoTokenFilter(stream);
    }

    
    
}

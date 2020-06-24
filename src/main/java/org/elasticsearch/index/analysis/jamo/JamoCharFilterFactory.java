package org.elasticsearch.index.analysis.jamo;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractCharFilterFactory;

import java.io.Reader;

public class JamoCharFilterFactory extends AbstractCharFilterFactory {

    public JamoCharFilterFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, name);
    }

    @Override
    public Reader create(Reader in) {
        return new JamoCharFilter(in);
    }
}

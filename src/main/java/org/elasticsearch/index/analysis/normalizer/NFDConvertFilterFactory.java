package org.elasticsearch.index.analysis.normalizer;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractCharFilterFactory;

import java.io.Reader;

public class NFDConvertFilterFactory extends AbstractCharFilterFactory {

    public NFDConvertFilterFactory(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
        super(indexSettings, name);
    }

    @Override
    public Reader create(Reader tokenStream) {
        return new NFDConvertFilter(tokenStream);
    }
}

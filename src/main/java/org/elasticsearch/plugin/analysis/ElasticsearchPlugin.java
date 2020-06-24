package org.elasticsearch.plugin.analysis;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.index.analysis.CharFilterFactory;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.index.analysis.chosung.ChosungTokenFilterFactory;
import org.elasticsearch.index.analysis.eng2kor.Eng2KorConvertFilterFactory;
import org.elasticsearch.index.analysis.higlight.HighlightTokenFilterFactory;
import org.elasticsearch.index.analysis.jamo.JamoCharFilterFactory;
import org.elasticsearch.index.analysis.jamo.JamoTokenFilterFactory;
import org.elasticsearch.index.analysis.kor2eng.Kor2EngConvertFilterFactory;
import org.elasticsearch.index.analysis.normalizer.NFDConvertFilterFactory;
import org.elasticsearch.index.analysis.soundex.SoundexConvertFilterFactory;
import org.elasticsearch.index.analysis.spell.JavacafeSpellFilterFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

/**
 * Javacafe에서 개발한 필터 리스트
 *
 * @author hrkim
 *
 */
public class ElasticsearchPlugin extends Plugin implements AnalysisPlugin {

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> getTokenFilters() {        
        Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> extra = new HashMap<>();
        
        // (1) 한글 자모 분석 필터
        extra.put("jamo_filter", JamoTokenFilterFactory::new);
        
        // (2) 한글 초성 분석 필터
        extra.put("chosung_filter", ChosungTokenFilterFactory::new);
        
        // (3) 영한 오타 변환 필터
        extra.put("eng2kor_filter", Eng2KorConvertFilterFactory::new);
        
        // (4) 한영 오타 변환 필터
        extra.put("kor2eng_filter", Kor2EngConvertFilterFactory::new);

        // (5) 한글 스펠링 체크 필터
        extra.put("spell_filter", JavacafeSpellFilterFactory::new);

        // (6) 한글 영문 발음 필터
        extra.put("soundex_filter", SoundexConvertFilterFactory::new);

        // (8) 하이라이트 필터
        extra.put("highlight_filter", HighlightTokenFilterFactory::new);

        return extra;
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<CharFilterFactory>> getCharFilters() {
        Map<String, AnalysisModule.AnalysisProvider<CharFilterFactory>> extra = new HashMap<>();
        extra.put("musinsa_char_nfd", NFDConvertFilterFactory::new);

        // 하일라이트용 캐릭터 필터
        extra.put("musinsa_char_jamo", JamoCharFilterFactory::new);

        return extra;
    }
}




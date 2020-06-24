package org.elasticsearch.index.analysis.higlight;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.elasticsearch.index.common.parser.KoreanJamoParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 한글 자모 분석 필터
 *
 */
public final class HighlightTokenFilter extends TokenFilter {

    private KoreanJamoParser parser;
    private CharTermAttribute termAtt;
    private PositionIncrementAttribute posIncAttr;
    private Queue<char[]> terms;


    public HighlightTokenFilter(TokenStream stream) {
        super(stream);
        this.parser = new KoreanJamoParser();
        this.termAtt = addAttribute(CharTermAttribute.class);
        this.posIncAttr = addAttribute(PositionIncrementAttribute.class);
        this.terms = new LinkedList<>();
    }

    /**
     * 한글 자모 Parser를 이용하여 토큰을 파싱하고 Term을 구한다.
     */
    @Override
    public boolean incrementToken() throws IOException {

        if (!terms.isEmpty()) {
            char[] buffer = terms.poll();
            termAtt.setEmpty();
            termAtt.copyBuffer(buffer, 0, buffer.length);
            posIncAttr.setPositionIncrement(0);
            return true;
        }

        if (!input.incrementToken()) {
            return false;
        } else {
            final char[] buffer = termAtt.buffer();
            final int termLength = termAtt.length();
            String tokenString = new String(buffer, 0, termLength);

            int length = tokenString.length();
            String previousWord = tokenString.substring(0,length-1);
            String lastWord = tokenString.substring(length-1,length);
            previousWord = parser.parse(previousWord);
            lastWord = parser.parse(lastWord);

            List<String> ngramList = this.setNgram(lastWord);
            for(String ngramTerm : ngramList){
                String word = previousWord + ngramTerm;
                terms.add(word.toCharArray());

            }
            return true;
        }
    }

    private List<String> setNgram(String terms) {
        //ㅂㅜ
        StringBuilder previousData = new StringBuilder();
        List<String> result = new ArrayList<>();
        int idx = 0;
        for (char term : terms.toCharArray()) {
            previousData.append(term);
            if (idx > 0) {
                result.add(previousData.toString());
            }else{
                result.add(String.valueOf(term));
            }
            idx++;
        }

        return result;
    }


}

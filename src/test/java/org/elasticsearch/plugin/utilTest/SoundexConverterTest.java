package org.elasticsearch.plugin.utilTest;

import org.elasticsearch.index.common.converter.SoundexConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SoundexConverterTest {
    @Test
    public void 한국어soundexTest1() {
        String token = "위원홰";

        SoundexConverter convert = new SoundexConverter();
        String result = convert.convert(token);

        System.out.println(result);
        assertEquals("ㅇㅣㅇㅝㄴㅎㅚ", result);
    }

    @Test
    public void 한국어soundexTest2() {
        String token = "남녘";

        SoundexConverter convert = new SoundexConverter();
        String result = convert.convert(token);

        System.out.println(result);
        assertEquals("ㄴㅏㅁㄴㅓㄱ", result);
    }

    @Test
    public void 한국어soundexTest3() {
        String token = "옆";

        SoundexConverter convert = new SoundexConverter();
        String result = convert.convert(token);

        System.out.println(result);
        assertEquals("ㅇㅓㅂ", result);
    }

    @Test
    public void test5() {
        String token = "한글1234";

        SoundexConverter convert = new SoundexConverter();
        String result = convert.convert(token);

        System.out.println(result);
        assertEquals("ㅎㅏㄴㄱㅡㄹ1234", result);
    }

    @Test
    public void test6() {
        String token = "english1234";

        SoundexConverter convert = new SoundexConverter();
        String result = convert.convert(token);

        System.out.println(result);
        assertEquals("english1234", result);
    }

    @Test
    public void test7() {
        String token = "$ㅎ한글1234";

        SoundexConverter convert = new SoundexConverter();
        String result = convert.convert(token);

        System.out.println(result);
        assertEquals("$ㅎㅎㅏㄴㄱㅡㄹ1234", result);
    }
}

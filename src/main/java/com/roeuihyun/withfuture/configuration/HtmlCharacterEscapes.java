/*=================================================================================
 *                        Copyright(c) 2022 WithFuture
 *
 * Project                : withMETAdata-boot
 * Source File Name       : com.withfuture.withmetadata.filter.HTMLCharacterEscapes
 * Description            : 출력시 Jackson 같은 Mapper를 통해 JSON 문자열로 Response에 담겨지므로, Mapper가 JSON 문자열을 생성할 때 XSS 방지 처리
 * Author                 : 남건우
 * Version                : 1.0.0
 * Created Date           : 2022.07.05
 * Updated Date           : 2022.07.05
 * Last Modifier          : 남건우
 * Updated Contents       : 
 * 2022.07.05 최초 작성
 *===============================================================================*/
package com.roeuihyun.withfuture.configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.LookupTranslator;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;

public class HtmlCharacterEscapes extends CharacterEscapes{
	
	private final int[] asciiEscapes;
	private final CharSequenceTranslator translator;
	
	public HtmlCharacterEscapes() {
		
		Map<CharSequence, CharSequence> customMap = new HashMap<>();
        customMap.put("(", "&#40;");
        customMap.put(")", "&#41;");
        customMap.put("#", "&#35;");
        customMap.put("\'", "&#39;");
        Map<CharSequence, CharSequence> CUSTOM_ESCAPE = Collections.unmodifiableMap(customMap);
        // 1. XSS 방지 처리할 특수 문자 지정
        asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
        asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['\"'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['('] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes[')'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['#'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;
        
        // XSS 방지 처리 특수 문자 인코딩 값 지정
        translator = new AggregateTranslator(
                new LookupTranslator(EntityArrays.BASIC_ESCAPE),  // <, >, &, " 는 여기에 포함됨
                new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE),
                new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE),
                new LookupTranslator(CUSTOM_ESCAPE)
        );

    }
	
	@Override
	public int[] getEscapeCodesForAscii() {
		return asciiEscapes;
	}

	@Override
	public SerializableString getEscapeSequence(int ch) {
		
		return new SerializedString(translator.translate(Character.toString((char) ch)));
		 // 참고 - 커스터마이징이 필요없다면 아래와 같이 Apache Commons Text에서 제공하는 메서드를 써도 된다.
//		return new SerializedString(StringEscapeUtils.escapeHtml4(Character.toString((char) ch)));
		
	}
	
}

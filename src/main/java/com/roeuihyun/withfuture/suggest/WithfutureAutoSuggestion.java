package com.roeuihyun.withfuture.suggest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.stream.IntStream;

import com.google.common.math.BigIntegerMath;

public class WithfutureAutoSuggestion {

	public static void main(String[] args) {
		WithfutureAutoSuggestionWithBigInteger was = new WithfutureAutoSuggestionWithBigInteger();
//		String input = "공통카테고리코드";
//		String originUserInput = "표준산업화도구코드";
//		String originUserInput = "메시지구분코드"; 
//		String originUserInput = "dfd메시지ad  sf구분dd코드ddd   ";
		String originUserInput = "산업화드";
//		String originUserInput = "표준asd산업화s도구내코드";
		System.out.println("originUserInput : " + originUserInput);
		// 2. 단어에 없는 문자 제외 처리
//		originUserInput = revisionTerm(originUserInput);
		
		char[] originUserInputCharacter = originUserInput.toCharArray();
		ArrayList<String> allWords = new ArrayList<String>();
		
		System.out.println("================================================Word Slice 처리 Start==============================================================");
		for(BigInteger totLoop = BigInteger.ZERO ; totLoop.compareTo(BigInteger.valueOf( originUserInputCharacter.length) ) < 0 ; totLoop = totLoop.add(BigInteger.ONE)) {
			for(BigInteger startIndex =  BigInteger.valueOf( originUserInputCharacter.length).subtract(totLoop).subtract(BigInteger.ONE); startIndex.compareTo(BigInteger.ZERO) >= 0 ; startIndex = startIndex.subtract(BigInteger.ONE)) {
				StringBuffer append = new StringBuffer();
				for(BigInteger endIndex = new BigInteger(startIndex.toString()); endIndex.compareTo(BigInteger.valueOf( originUserInputCharacter.length).subtract(totLoop)) < 0 ; endIndex = endIndex.add(BigInteger.ONE)) {
//					System.out.println(endIndex.compareTo(BigInteger.valueOf( originUserInputCharacter.length).subtract(totLoop)));
//					System.out.println("totLoop : " + totLoop + " , startIndex : " + startIndex + " , endIndex : " + endIndex + " , originUserInputCharacter.length : " + originUserInputCharacter.length);
					append.append(originUserInputCharacter[endIndex.intValue()]);
				}
				System.out.println(append.toString());
				allWords.add(append.toString());
			}			
		}
		System.out.println("================================================Word Slice 처리 End==============================================================");
		
		System.out.println("=====================================in String or OrString구문 만들기 Start=========================================================");
		System.out.println("=====================================in String or OrString구문 만들기 End=========================================================");
		// in 구문 만들기
		ArrayList<String> allWordsList = was.getAllItemLists();
		
		System.out.println("=====================================단어에 없는 문자 제외 처리 Start=========================================================");
		System.out.println("originUserInput : " + originUserInput);
		HashMap<String,Object> revisionTermParam = new HashMap<String,Object>();
		revisionTermParam.put("originUserInput", originUserInput);		
		revisionTermParam.put("allWordsList", allWordsList);
		originUserInput = was.revisionTerm(revisionTermParam);
		System.out.println("=====================================단어에 없는 문자 제외 처리 End=========================================================");		
		
		System.out.println("================================================Word Replace 처리 Start==============================================================");
		System.out.println("Looping factorial Count : " + BigIntegerMath.factorial(allWordsList.size()).intValue());
		System.out.println("Looping Count : " + BigInteger.valueOf(allWordsList.size() * 100));
		TreeSet<String> suggestResult = new TreeSet<String>(); 
//		for(BigInteger totSugesstCnt = BigInteger.ZERO ; totSugesstCnt.compareTo(BigIntegerMath.factorial(allWordsList.size())) < 0 ; totSugesstCnt = totSugesstCnt.add(BigInteger.ONE)) {
		for(BigInteger totSugesstCnt = BigInteger.ZERO ; totSugesstCnt.compareTo(BigInteger.valueOf(allWordsList.size() * 100)) < 0 ; totSugesstCnt = totSugesstCnt.add(BigInteger.ONE)) {
			String replaceUserInput = originUserInput;
			int originUserInputLength = originUserInput.toCharArray().length;
			int replaceUserInputLength = replaceUserInput.toCharArray().length;
			ArrayList<Boolean> inputReplace = new ArrayList<Boolean>();
			for(int wordParsingMarkerIndex = 0; wordParsingMarkerIndex < originUserInputLength; wordParsingMarkerIndex++) {
				inputReplace.add(false);
			}
			
			for(int wordIndex = 0 ; wordIndex < allWordsList.size() ; wordIndex++) {
				String word = allWordsList.get(wordIndex);
				replaceUserInputLength = replaceUserInput.toCharArray().length;
				int wordLength = word.toCharArray().length;
				int originWordStartIndex = originUserInput.indexOf(word);
				int originWordEndIndex = originUserInput.indexOf(word) + wordLength;
				int wordStartIndex = replaceUserInput.indexOf(word);
				int wordEndIndex = replaceUserInput.indexOf(word) + wordLength;
//				System.out.println("totSugesstCnt : " + totSugesstCnt 
//								 + ", wordIndex : " + wordIndex
//								 + ", word : " + word
//								 + ", originUserInput : " + originUserInput 
//								 + ", originUserInputLength : " + originUserInputLength
//								 + ", originWordStartIndex : " + originWordStartIndex 
//								 + ", originWordEndIndex : " + originWordEndIndex
//								 + ", replaceUserInput : " + replaceUserInput 
//								 + ", replaceUserInputLength : " + replaceUserInputLength 
//								 + ", wordStartIndex : " + wordStartIndex 
//								 + ", wordEndIndex : " + wordEndIndex 
//								 );
				WordContainIf :
				if( replaceUserInput.contains(word) ) {
					for(int replaceIndex = originWordStartIndex; replaceIndex < originWordEndIndex; replaceIndex ++) {
						if(inputReplace.get(replaceIndex)) {
							break WordContainIf;
						}
					}
					for(int replaceIndex = originWordStartIndex; replaceIndex < originWordEndIndex; replaceIndex ++) {
						inputReplace.set(replaceIndex,true);
					}
					if( wordStartIndex == 0 && wordEndIndex != replaceUserInputLength) {
//						System.out.println("맨 왼쪽에 단어가 있을 경우 : "  + replaceUserInput.substring(wordStartIndex,wordEndIndex)+ "_");
						replaceUserInput = word + "_" + replaceUserInput.substring(wordEndIndex,replaceUserInputLength);
					}else if( wordStartIndex != 0 && wordEndIndex < replaceUserInputLength) {
//						System.out.println("단어가 가운데에 있을 경우 :  "  + word + "_");
						replaceUserInput = replaceUserInput.substring(0,wordStartIndex) + word + "_" + replaceUserInput.substring(wordEndIndex,replaceUserInputLength);
					}else if( wordStartIndex != 0 && wordEndIndex == replaceUserInputLength){
//						System.out.println("맨 오른쪽에 단어가 있을 경우 : "  + replaceUserInput.substring(wordStartIndex,wordEndIndex));
					}else {
//						System.out.println("wordStartIndex == 0 && wordEndIndex == replaceUserInputLength);
//						System.out.println("단어가 용어인 경우 : "  + replaceUserInput.substring(wordStartIndex,wordEndIndex));						
					}
				}
			}
			
			Collections.shuffle(allWordsList);
			suggestResult.add(replaceUserInput);
			
		}
		System.out.println("================================================Word Replace 처리 End==============================================================");
		
		System.out.println("================================================추천 용어 Start==============================================================");
//		Iterator<String> keys = suggestResult.iterator(); // Iterator 사용
//		while(keys.hasNext()) { //값이 있으면 true 없으면 false
//		    System.out.println("suggestResult : " + keys.next());
//		}
//		suggestResult.stream().forEach(null);
//		suggestResult.forEach(word -> System.out.println("suggestResult : "+word));
		suggestResult.stream().sorted(Comparator.reverseOrder()).forEach(word -> System.out.println("suggestResult : "+ word ));

		System.out.println("================================================추천 용어 End==============================================================");
		
		System.out.println("================================================추천 용어 우선순위 높은 5개 추출 Start==============================================================");
		System.out.println(suggestResult);
		ArrayList<String> suggestResultList = new ArrayList<String>();
		IntStream.range(0, 5).forEach(idx -> {
			if(!suggestResult.isEmpty()) {
				suggestResultList.add(idx,suggestResult.pollLast());
			}
        });
		System.out.println(suggestResultList);
		System.out.println("================================================추천 용어 우선순위 높은 5개 추출 End==============================================================");
		
	}
	
	private ArrayList<String> getAllItemLists(){
		ArrayList<String> list = new ArrayList<String>();
		//공통카테고리코드
//		list.add("공통");
//		list.add("공통카테고리");
//		list.add("카테고리");
//		list.add("카테고리코드");
//		list.add("코드");

		//표준산업화도구코드
		list.add("산업화도구");
		list.add("표준산업화");
		list.add("도구코드");
		list.add("산업화");
		list.add("코드");
		list.add("표준");
		list.add("도구");
		list.add("코");
		list.add("드");
		
		//메시지구분코드
//		list.add("메시지");
//		list.add("구분코드");
//		list.add("구분");
//		list.add("코드");
//		list.add("메시");
//		list.add("지");
//		list.add("지구");
//		list.add("구");
//		list.add("분");
		
		return list;
	}
	
	// 단어에 없는 문자 제외 처리
	private String revisionTerm(HashMap<String,Object> revisionTermParam) {
		String reqTerm = (String)revisionTermParam.get("originUserInput");
		ArrayList<String> allWordsList = (ArrayList<String>)revisionTermParam.get("allWordsList");
		String revisionTerm = reqTerm;
		String searchTerm = reqTerm;
		char[] searchTermArr = null;
		String splitTerm = null;
		boolean isNotFindWord = false;
		while(searchTerm.length() > 0) {
			searchTermArr = searchTerm.toCharArray();
			for(int i = searchTermArr.length; i > 0; i--) {
				splitTerm = searchTerm.substring(0, i);
				if(allWordsList.contains(splitTerm)) {
					searchTerm = searchTerm.substring(i); 
					isNotFindWord = false;
					break;
				} else {
					isNotFindWord = true;	
				}
			}
			searchTerm = searchTerm.replaceAll(splitTerm, "");
			if(isNotFindWord) {
				revisionTerm = revisionTerm.replaceAll(splitTerm, "");	
				searchTerm = revisionTerm;
			}
		}
		System.out.println("revisionTerm : " + revisionTerm );
		return revisionTerm;
	}

}
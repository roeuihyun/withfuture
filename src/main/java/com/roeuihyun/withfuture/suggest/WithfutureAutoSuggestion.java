package com.roeuihyun.withfuture.suggest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

import com.google.common.math.BigIntegerMath;

public class WithfutureAutoSuggestion {

	public static void main(String[] args) {
		WithfutureAutoSuggestion was = new WithfutureAutoSuggestion();
//		String input = "공통카테고리코드";
		String originUserInput = "표준산업화도구코드";
		char[] originUserInputCharacter = originUserInput.toCharArray();
		ArrayList<String> allWords = new ArrayList<String>();
		
		System.out.println("================================================Word Slice 처리 Start==============================================================");
		for(int totLoop = 0 ; totLoop < originUserInputCharacter.length ; totLoop ++) {
			for(int startIndex =  originUserInputCharacter.length - totLoop -1; startIndex >= 0 ; startIndex --) {
				StringBuffer append = new StringBuffer();
				for(int endIndex = startIndex; endIndex < (originUserInputCharacter.length - totLoop) ; endIndex ++) {
//					System.out.println("totLoop : " + totLoop + " , startIndex : " + startIndex + " , endIndex : " + endIndex + " , originUserInputCharacter.length : " + originUserInputCharacter.length);
					append.append(originUserInputCharacter[endIndex]);
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
		
		System.out.println("================================================Word Replace 처리 Start==============================================================");
		System.out.println("Looping Count : " + BigIntegerMath.factorial(allWordsList.size()).intValue());
		TreeSet<String> suggestResult = new TreeSet<String>(); 
		for(int totSugesstCnt = 0 ; totSugesstCnt < BigIntegerMath.factorial(allWordsList.size()).intValue() ; totSugesstCnt ++) {
			String replaceUserInput = originUserInput;
			originUserInput = originUserInput.replace(" ", ""); 
			replaceUserInput = replaceUserInput.replace(" ", "");
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
			
//			System.out.println("");
//			System.out.println("input : " + input);
//			for(int wordParsingMarkerIndex = 0; wordParsingMarkerIndex < inputLength; wordParsingMarkerIndex++) {
//				System.out.print(inputReplace.get(wordParsingMarkerIndex));
//			}
//			System.out.println("");
//			
			suggestResult.add(replaceUserInput);
//			System.out.println("replaceUserInput : " + replaceUserInput);
//			System.out.println("");
			
		}
		System.out.println("================================================Word Replace 처리 End==============================================================");
		
		System.out.println("================================================추천 용어 Start==============================================================");
		Iterator<String> keys = suggestResult.iterator(); // Iterator 사용
		while(keys.hasNext()) { //값이 있으면 true 없으면 false
		    System.out.println("suggestResult : " + keys.next());
		}
		System.out.println("================================================추천 용어 End==============================================================");
		
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
		
		return list;
	}

}

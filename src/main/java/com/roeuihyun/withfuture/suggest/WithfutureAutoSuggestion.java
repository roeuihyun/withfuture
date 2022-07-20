package com.roeuihyun.withfuture.suggest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class WithfutureAutoSuggestion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WithfutureAutoSuggestion was = new WithfutureAutoSuggestion();
//		String input = "공통카테고리코드";
		String input = "표준산업화도구코드";
		char[] inputCharacter = input.toCharArray();
		ArrayList<String> allWords = new ArrayList<String>();
		
		System.out.println("==============================================================================================================");
		for(int totLoop = 0 ; totLoop < inputCharacter.length ; totLoop ++) {
			for(int startIndex =  inputCharacter.length - totLoop -1; startIndex >= 0 ; startIndex --) {
				StringBuffer append = new StringBuffer();
				for(int endIndex = startIndex; endIndex < (inputCharacter.length - totLoop) ; endIndex ++) {
					append.append(inputCharacter[endIndex]);
				}
				System.out.println(append.toString());
				allWords.add(append.toString());
			}			
		}
		System.out.println("==============================================================================================================");
		
		System.out.println("=====================================in String or OrString구문 만들기=========================================================");
		// in 구문 만들기
		System.out.println("==============================================================================================================");
		ArrayList<String> allWordsList = was.getAllItemLists();
		
		System.out.println("==============================================================================================================");
		TreeSet<String> suggestResult = new TreeSet<String>(); 
		for(int totSugesstCnt = 0 ; totSugesstCnt < 20 ; totSugesstCnt ++) {
			String input2 = input;
			int inputLength = input.toCharArray().length;
			int input2Length = input2.toCharArray().length;
			ArrayList<String> allWordsListCopy = (ArrayList<String>)(allWordsList.clone());
			ArrayList<Integer> removeIndexList = new ArrayList<Integer>();
			ArrayList<Boolean> inputReplace = new ArrayList<Boolean>();
			for(int wordParsingMarkerIndex = 0; wordParsingMarkerIndex < inputLength; wordParsingMarkerIndex++) {
				inputReplace.add(false);
			}
			
			for(int wordIndex = 0 ; wordIndex < allWordsList.size() ; wordIndex++) {
				String word = allWordsList.get(wordIndex);
				input2Length = input2.toCharArray().length;
				int wordLength = word.toCharArray().length;
				int originWordStartIndex = input.indexOf(word);
				int originWordEndIndex = input.indexOf(word) + wordLength;
				int wordStartIndex = input2.indexOf(word);
				int wordEndIndex = input2.indexOf(word) + wordLength;
				System.out.println("totSugesstCnt : " + totSugesstCnt 
								 + ", wordIndex : " + wordIndex
								 + ", word : " + word
								 + ", input : " + input 
								 + ", inputLength : " + inputLength
								 + ", originWordStartIndex : " + originWordStartIndex 
								 + ", originWordEndIndex : " + originWordEndIndex
								 + ", input2 : " + input2 
								 + ", input2Length : " + input2Length 
								 + ", wordStartIndex : " + wordStartIndex 
								 + ", wordEndIndex : " + wordEndIndex 
								 );
				WordContainIf :
				if( input2.contains(word) ) {
					
					if( wordStartIndex == 0 && wordEndIndex != input2Length) {
						for(int replaceIndex = originWordStartIndex; replaceIndex < originWordEndIndex; replaceIndex ++) {
							if(inputReplace.get(replaceIndex)) {
								break WordContainIf;
							}else {
								inputReplace.set(replaceIndex,true);
							}
						}
						System.out.println("맨 왼쪽에 단어가 있을 경우 : "  + input2.substring(wordStartIndex,wordEndIndex)+ "_");
						input2 = word + "_" + input2.substring(wordEndIndex,input2Length);
					}else if( wordStartIndex != 0 && wordEndIndex < input2Length) {
						for(int replaceIndex = originWordStartIndex; replaceIndex < originWordEndIndex; replaceIndex ++) {
							if(inputReplace.get(replaceIndex)) {
								break WordContainIf;
							}else {
								inputReplace.set(replaceIndex,true);
							}
						}
						System.out.println("단어가 가운데에 있을 경우 :  "  + word + "_");
						input2 = input2.substring(0,wordStartIndex) + word + "_" + input2.substring(wordEndIndex,input2Length);
					}else if( wordStartIndex != 0 && wordEndIndex == input2Length){
						for(int replaceIndex = originWordStartIndex; replaceIndex < originWordEndIndex; replaceIndex ++) {
							if(inputReplace.get(replaceIndex)) {
								break WordContainIf;
							}else {
								inputReplace.set(replaceIndex,true);
							}
						}
						System.out.println("맨 오른쪽에 단어가 있을 경우 : "  + input2.substring(wordStartIndex,wordEndIndex));
					}
					allWordsListCopy.add(allWordsList.get(wordIndex));
					removeIndexList.add(wordIndex);
				}
			}
			
			System.out.println("");
			System.out.println("input : " + input);
			for(int wordParsingMarkerIndex = 0; wordParsingMarkerIndex < inputLength; wordParsingMarkerIndex++) {
				System.out.print(inputReplace.get(wordParsingMarkerIndex));
			}
			System.out.println("");
			
			suggestResult.add(input2);
			System.out.println("input2 : " + input2);
			System.out.println("");
			
			for(int removeIndex = removeIndexList.size()-1; removeIndex >= 0; removeIndex -- ) {
				allWordsListCopy.remove((int)removeIndexList.get(removeIndex));
			}
			
			allWordsList = (ArrayList<String>)allWordsListCopy.clone();
			
		}
		System.out.println("==============================================================================================================");
		
		Iterator keys = suggestResult.iterator();	// Iterator 사용
		while(keys.hasNext()) {//값이 있으면 true 없으면 false
		    System.out.println("suggestResult : " + keys.next());
		}
		
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
		list.add("도구코드");
		list.add("도구");
		list.add("산업화");
		list.add("산업화도구");		
		list.add("코드");
		list.add("표준");
		list.add("표준산업화");
		
		return list;
	}

}

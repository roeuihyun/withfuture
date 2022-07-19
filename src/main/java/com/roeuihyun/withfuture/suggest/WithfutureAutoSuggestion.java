package com.roeuihyun.withfuture.suggest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class WithfutureAutoSuggestion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WithfutureAutoSuggestion was = new WithfutureAutoSuggestion();
		String input = "공통카테고리코드";
		char[] inputCharacter = input.toCharArray();
		ArrayList<String> allWords = new ArrayList<String>(); 
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
		List distinctAllWords = allWords.stream().sorted().collect(Collectors.toList());
		for(int i = 0; i < distinctAllWords.size() ; i ++) {
			System.out.println(distinctAllWords.get(i));
		}
		
		// in 구문 만들기
		System.out.println("==============================================================================================================");
		ArrayList<HashMap<String,Object>> allWordsList = was.getAllItemLists();
		
		for(int showIndex = 0 ; showIndex < allWordsList.size(); showIndex ++) {
			System.out.println(" allWordsList.get(showIndex) : " + allWordsList.get(showIndex));
		}
		
//		for(int i = 0 ; i < 5 ; i ++) {
//			StringBuffer suggestWord = new StringBuffer();
//			char[] inputCharacter2 = input.toCharArray();
//			for(int startIndex =  inputCharacter2.length -1; startIndex >= 0 ; startIndex --) {
//				StringBuffer append = new StringBuffer();
//				for(int endIndex = startIndex; endIndex < (inputCharacter2.length ) ; endIndex ++) {
//					append.append(inputCharacter2[endIndex]);
//					System.out.println("startIndex :  " + startIndex + " endIndex : " + endIndex + "append.toString() : " + append.toString());
//					for(int wordIndex = 0; wordIndex < allWordsList.size() ; wordIndex ++) {
//						if(allWordsList.get(wordIndex).get("word").equals(append.toString())) {
//							System.out.println("allWordsList.get(wordIndex).get(\"word\") : " + allWordsList.get(wordIndex).get("word") +  " append.toString() :  " + append.toString());
////							System.out.println(inputCharacter2.toString().substring(1,startIndex));
////							System.out.println(inputCharacter2.toString().substring(startIndex,endIndex));
//							inputCharacter2 = String.valueOf(inputCharacter2).substring(0,startIndex).toCharArray();
//							append.append("_");
//							allWordsList.add(allWordsList.get(wordIndex));
//							allWordsList.remove(wordIndex);
//							suggestWords.add(append.toString());
//						}
////						System.out.println(" allWordsList.get(wordIndex).get(\"word\") : " + allWordsList.get(wordIndex).get("word") +  " append.toString() :  " + append.toString() + " index : " + wordIndex);
//					}
//				}
//			}	
//			
//		}
		TreeSet<String> suggestResult = new TreeSet<String>(); 
		for(int totSugesstCnt = 0 ; totSugesstCnt < 5 ; totSugesstCnt ++) {
			String input2 = input;
			int inputLength = input.toCharArray().length;
			int input2Length = input2.toCharArray().length;
			ArrayList<HashMap<String,Object>> allWordsListCopy = (ArrayList<HashMap<String,Object>>)(allWordsList.clone());
			ArrayList<Integer> removeIndexList = new ArrayList<Integer>();
			ArrayList<Boolean> inputReplace = new ArrayList<Boolean>();
			for(int wordParsingMarkerIndex = 0; wordParsingMarkerIndex < inputLength; wordParsingMarkerIndex++) {
				inputReplace.add(false);
			}
//			ArrayList<Integer> allWordsListRemoveIndex = new ArrayList<Integer>(); 
			WordLoop :
			for(int wordIndex = 0 ; wordIndex < allWordsList.size() ; wordIndex++) {
				String word = (String)allWordsList.get(wordIndex).get("word");
				input2Length = input2.toCharArray().length;
				int wordLength = word.toCharArray().length;
				int originWordStartIndex = input.indexOf(word);
				int originWordEndIndex = input.indexOf(word) + wordLength;
				int wordStartIndex = input2.indexOf(word);
				int wordEndIndex = input2.indexOf(word) + wordLength;
				System.out.println("totSugesstCnt : " + totSugesstCnt 
								 + ", wordIndex : " + wordIndex
								 + ", input : " + input 
								 + ", inputLength : " + inputLength 
								 + ", input2 : " + input2 
								 + ", input2Length : " + input2Length 
								 + ", word : " + word
								 + ", originWordStartIndex : " + originWordStartIndex 
								 + ", originWordEndIndex : " + originWordEndIndex
								 + ", wordStartIndex : " + wordStartIndex 
								 + ", wordEndIndex : " + wordEndIndex 
								 );
				WordContainIf :
				if( input.contains(word) ) {
					
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
//						input2 = input2.substring(0,wordStartIndex) +  input2.substring(wordStartIndex,wordEndIndex);
					}
//					allWordsList.add(allWordsList.get(j));
//					allWordsListRemoveIndex.add(j);
					
					allWordsListCopy.add(allWordsList.get(wordIndex));
//					allWordsListCopy.remove(wordIndex);
					removeIndexList.add(wordIndex);
				}
			}
			suggestResult.add(input2);
			System.out.println("");
			
//			for(int removeIndex = 0; removeIndex < allWordsListRemoveIndex.size(); removeIndex ++ ) {
//				allWordsList.remove(allWordsListRemoveIndex.get(removeIndex));  
//			}

			for(int showIndex = 0 ; showIndex < allWordsListCopy.size(); showIndex ++) {
				System.out.println(" before allWordsListCopy.get(showIndex) : " + allWordsListCopy.get(showIndex));
			}
			
			for(int removeIndex = removeIndexList.size()-1; removeIndex >= 0; removeIndex -- ) {
				System.out.println(" removeIndexList.get(removeIndex) : " + removeIndexList.get(removeIndex));
				allWordsListCopy.remove((int)removeIndexList.get(removeIndex));
				System.out.println("remove target allWordsListCopy.get(i): ");
			}
			
			for(int showIndex = 0 ; showIndex < allWordsListCopy.size(); showIndex ++) {
				System.out.println(" after allWordsListCopy.get(showIndex) : " + allWordsListCopy.get(showIndex));
			}
			
			allWordsList = (ArrayList<HashMap<String,Object>>)allWordsListCopy.clone();
			
		}
		
		Iterator keys = suggestResult.iterator();	// Iterator 사용
		while(keys.hasNext()) {//값이 있으면 true 없으면 false
		    System.out.println("suggestResult : " + keys.next());
		}
		
	}
	
	private ArrayList<HashMap<String,Object>> getAllItemLists(){
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> word1 = new HashMap<String,Object>();
		word1.put("word", "공통");
		list.add(word1);
		HashMap<String,Object> word2 = new HashMap<String,Object>();
		word2.put("word", "카테고리");
		list.add(word2);
		HashMap<String,Object> word3 = new HashMap<String,Object>();
		word3.put("word", "코드");
		list.add(word3);
		HashMap<String,Object> word4 = new HashMap<String,Object>();
		word4.put("word", "공통카테고리");
		list.add(word4);
		HashMap<String,Object> word5 = new HashMap<String,Object>();
		word5.put("word", "카테고리코드");
		list.add(word5);
		return list;
	}

}

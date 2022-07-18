package com.roeuihyun.withfuture.suggest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		ArrayList<String> suggestWords = new ArrayList<String>();
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
		
		for(int i = 0 ; i < 5 ; i ++) {
			StringBuffer suggestWord = new StringBuffer();
			String input2 = input;
			ArrayList<HashMap<String,Object>> allWordsListCopy = (ArrayList<HashMap<String,Object>>)(allWordsList.clone()); 
			for(int j = 0 ; j < allWordsList.size() ; j++) {
				String word = (String)allWordsList.get(j).get("word");
				int wordLength = word.toCharArray().length;
				int inputLength = input2.toCharArray().length;
				int wordStartIndex = input2.indexOf(word);
				int wordEndIndex = (input2.indexOf(word) + wordLength);
				int compareIndex = 0;
				if( input2.contains(word) ) {
					System.out.println("i : " + i + ",j : " + j +  ", input2 : " + input2 + ", inputLength : " + inputLength +", word : " + word + ", wordStartIndex : "  + wordStartIndex +  ", wordEndIndex : "  + wordEndIndex );
					if( wordStartIndex == 0 && wordEndIndex != inputLength) {
						System.out.println(" wordStartIndex == 0 "  + input2.substring(wordStartIndex,wordEndIndex)+ "_");
						suggestWord.append(input2.substring(wordStartIndex,wordEndIndex)+ "_");
						input2 = input2.substring(wordEndIndex,inputLength);
					}else if( wordEndIndex == inputLength) {
						System.out.println(" wordEndIndex == inputLength "  + input2.substring(wordStartIndex,wordEndIndex));
						suggestWord.append(input2.substring(wordStartIndex,wordEndIndex));
						input2 = input2.substring(0,wordStartIndex);
					}else {
						System.out.println(" else "  + input2.substring(wordStartIndex,wordEndIndex) + "_");
						suggestWord.append(input2.substring(wordStartIndex,wordEndIndex) + "_");
						input2 = input2.substring(0,wordStartIndex) + input2.substring(wordEndIndex,inputLength);
					}
					inputLength = input2.toCharArray().length;
					allWordsListCopy.add(allWordsList.get(j));
					allWordsListCopy.remove(j);
				}
			}
			allWordsList = (ArrayList<HashMap<String,Object>>)allWordsListCopy.clone();
//			System.out.println("suggestWord.toString() : " + suggestWord.toString());
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

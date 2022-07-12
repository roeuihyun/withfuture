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
		int count = 0;
		for(int totLoop = 0 ; totLoop < inputCharacter.length ; totLoop ++) {
			for(int endIndex =  inputCharacter.length - totLoop -1; endIndex >= 0 ; endIndex --) {
				StringBuffer append = new StringBuffer();
				for(int startIndex = endIndex; startIndex < (inputCharacter.length - totLoop) ; startIndex ++) {
					append.append(inputCharacter[startIndex]);
					count ++;
				}
				System.out.println(append.toString());
				allWords.add(append.toString());
			}			
		}
		
		System.out.println("count : " + count);
		
		List distinctAllWords = allWords.stream().sorted().collect(Collectors.toList());
		System.out.println(distinctAllWords.size());
		for(int i = 0; i < distinctAllWords.size() ; i ++) {
			System.out.println(distinctAllWords.get(i));
		}
		
		// in 구문 만들기
		
		ArrayList<HashMap<String,Object>> allWordsList = was.getAllItemLists();
		
		for( int i = 0 ; i < allWordsList.size(); i ++) {
			
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

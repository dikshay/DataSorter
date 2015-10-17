package com.dikshay.clasificacion.sorting;

import java.util.Vector;

public class SortingAlgorithms {


	public Vector<Integer> InsertionSort(Vector<Integer> pElementsToBeSorted)
	{
		int key,i;
		for(int j = 1;j<pElementsToBeSorted.size();j++)
			{
				key = pElementsToBeSorted.get(j);
				i = j-1;

				while(i >= 0 && pElementsToBeSorted.get(i) > key)
					{	
						pElementsToBeSorted.remove(i+1);
						pElementsToBeSorted.add(i+1,pElementsToBeSorted.get(i));
						i = i-1;

					}

					
				pElementsToBeSorted.remove(i+1);
				pElementsToBeSorted.add(i+1,key);
			}
		return pElementsToBeSorted;
	}
	public void MergeSort()
	{
		
	}
	public void QuickSort()
	{
		
	}
	public void HeapSort()
	{
		
	}
	public void CountingSort()
	{
		
	}
	public void RadixSort()
	{
		
	}
	public void BucketSort()
	{
		
	}
}

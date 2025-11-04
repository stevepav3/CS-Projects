#pragma once

#include <iostream>
#include <vector>
#include "HashTableFunction.h"

class HashTable
{
public:
	HashTable(int size, HashTableFunction * function);
	~HashTable();

	void Insert(int value);
	void Erase(int value);
	int * Find(int value);
	friend std::ostream& operator <<(std::ostream& ostr, const HashTable& table);

private:
	std::vector<std::vector<int>> Table;
	HashTableFunction * Function;
};
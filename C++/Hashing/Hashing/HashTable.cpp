#include <algorithm>
#include "HashTable.h"

HashTable::HashTable(int size, HashTableFunction * function)
	: Table(size),
	  Function(function)
{
	// Nothing here...
}

HashTable::~HashTable()
{
	delete Function;
}

void HashTable::Insert(int value)
{
	int location = Function->Hash(value);
	Table[location].push_back(value);
}

void HashTable::Erase(int value)
{
	int location = Function->Hash(value);
	
	std::vector<int>::iterator iter =
		std::remove(Table[location].begin(), Table[location].end(), value);
	
	Table[location].erase(iter, Table[location].end());
}

int * HashTable::Find(int value)
{
	int location = Function->Hash(value);
	std::vector<int>::iterator iter =
		std::find(Table[location].begin(), Table[location].end(), value);
	
	return (iter != Table[location].end()) ? &(*iter) : nullptr;
}

std::ostream& operator<<(std::ostream& ostr, const HashTable& table)
{
	for (std::size_t i = 0; i < table.Table.size(); ++i)
	{
		ostr << i << ": ";
		for (int j : table.Table[i])
		{
			ostr << j << " ";
		}
		ostr << std::endl;
	}
	return ostr;
}
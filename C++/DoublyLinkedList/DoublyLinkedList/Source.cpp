// STEVE PAV

#include <iostream>
#include "LinkedList.h"

void TestPushFront(LinkedList& list)
{
	short value;
	std::cout << "Enter value to push to front: ";
	std::cin >> value;

	list.PushFront(value);
	std::cout << list << std::endl;
}


void TestPopFront(LinkedList& list)
{
	list.PopFront();
	std::cout << list << std::endl;
}


void TestPrintList(LinkedList& list)
{
	std::cout << list << std::endl;
}


void TestBracketOperator(LinkedList& list)
{
	int index;
	std::cout << "Enter index value: ";
	std::cin >> index;
	std::cout << "Value at list[" << index << "] = " << list[index] << std::endl;
}


void TestEmpty(LinkedList& list)
{
	if (list.empty())
	{
		std::cout << "List is empty." << std::endl;
	}
	else
	{
		std::cout << "List is not empty." << std::endl;
	}
}


void TestSize(LinkedList& list)
{
	std::cout << "Size of list is " << list.size() << std::endl;
}


void TestInsert(LinkedList& list)
{
	std::cout << "Enter value to insert: ";
	short value;
	std::cin >> value;

	std::cout << "Enter position index to insert " << value << " at: ";
	int index;
	std::cin >> index;

	list.insert(index, value);
	std::cout << list << std::endl;
}


void TestErase(LinkedList& list)
{
	std::cout << "Enter position index to erase: ";
	int index;
	std::cin >> index;

	list.erase(index);
	std::cout << list << std::endl;
}


void TestFind(LinkedList& list)
{
	std::cout << "Enter value to search for: ";
	short value;
	std::cin >> value;

	if (list.find(value) != -1)
	{
		std::cout << "Value found at index " << list.find(value) << std::endl;
	}
	else
	{
		std::cout << "Value not found" << std::endl;
	}
}


void PrintMenu()
{
	std::cout << std::endl;
	std::cout << "Enter choice to test something" << std::endl;
	std::cout << "1. Test PushFront" << std::endl;
	std::cout << "2. Test PopFront" << std::endl;
	std::cout << "3. Test << operator by printing list" << std::endl;
	std::cout << "4. Test [] operator by getting value stored in index" << std::endl;
	std::cout << "5. Test empty" << std::endl;
	std::cout << "6. Test size" << std::endl;
	std::cout << "7. Test insert" << std::endl;
	std::cout << "8. Test erase" << std::endl;
	std::cout << "9. Test find" << std::endl;
	std::cout << "0. Quit" << std::endl;
	std::cout << "Enter choice: ";
}


int main()
{
	int input = -1;
	LinkedList list;

	while (input != 0)
	{
		PrintMenu();

		std::cin >> input;

		while (input < 0 || input > 9)
		{
			std::cout << "Enter valid choice: ";
			std::cin >> input;
		}

		switch (input)
		{
		case 1: TestPushFront(list); break;

		case 2: TestPopFront(list); break;

		case 3: TestPrintList(list); break;

		case 4: TestBracketOperator(list); break;

		case 5: TestEmpty(list); break;

		case 6: TestSize(list); break;

		case 7: TestInsert(list); break;

		case 8: TestErase(list); break;

		case 9: TestFind(list);
		}

	}
	
	return 0;
}
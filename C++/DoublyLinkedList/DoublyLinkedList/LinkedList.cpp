// STEVE PAV

#include "LinkedList.h"

LinkedList::LinkedList()
	: Head(nullptr)
{
	// Nothing here
}

LinkedList::~LinkedList()
{
	Clear();
}


// Clear list and delete dynamic memory
void LinkedList::Clear()
{
	LinkedListNode* Current = Head;
	while (Current != nullptr)
	{
		LinkedListNode* temp = Current->Next;
		delete Current;
		Current = temp;
	}
	Head = nullptr;
}


// Place value at front of list
void LinkedList::PushFront(short value)
{
	LinkedListNode* Temp = new LinkedListNode(value);

	Temp->Next = Head;
	Temp->Previous = nullptr;
	Head = Temp;

	// If there is another value after newly added one,
	// connect next value's previous pointer to newly added value
	if (Temp->Next != nullptr)
	{
		Temp->Next->Previous = Head;
	}
}


// Remove front value from list
void LinkedList::PopFront()
{
	// Empty case
	if (Head == nullptr)
	{
		throw std::out_of_range("Tried to pop empty linked list!");
	}

	// Size is 1 case
	if (Head->Next == nullptr)
	{
		delete Head;
		Head = nullptr;
	}

	// Size greater than 1 case
	else
	{
		LinkedListNode* Current = Head;
		Head = Current->Next;
		Current->Next->Previous = nullptr;

		delete Current;
	}
}

// Determine if list is empty
bool LinkedList::empty()
{
	return Head == nullptr;
}

// Return size of list using pointers
int LinkedList::size()
{
	int size = 0;
	
	LinkedListNode* Current = Head;
	
	// Empty case
	if (Current == nullptr)
	{
		return size;
	}

	// Non-empty case
	while (Current->Next != nullptr)
	{
		size++;
		Current = Current->Next;
	}

	// Add one to size since loop doesn't go through for last value in list
	size += 1;

	return size;
}

// Insert value to list at given index
void LinkedList::insert(int positionIndex, short value)
{
	// Empty case
	if (Head == nullptr)
	{
		PushFront(value);
	}

	// Non-empty case
	else
	{
		LinkedListNode* Current = Head;
		LinkedListNode* Temp = new LinkedListNode(value);

		// PositionIndex >= linked list size gets inserted at the back of the linked list
		if (positionIndex >= size())
		{
			positionIndex = size() - 1;
		}

		// Get list to index given
		for (int i = 0; i < positionIndex - 1; i++)
		{
			Current = Current->Next;
		}

		// Set all four linkages
		Temp->Next = Current->Next;
		Temp->Previous = Current;
		Temp->Previous->Next = Temp;
		Temp->Next->Previous = Temp;

	}

}


// Erase value at given index
void LinkedList::erase(int positionIndex)
{
	// Empty case
	if (Head == nullptr)
	{
		PopFront();
	}

	if (positionIndex >= size() || positionIndex < 0)
	{
		throw std::out_of_range("Cannot erase value outside of list range");
	}

	// Non-empty case
	else
	{
		LinkedListNode* Current = Head;
		for (int i = 0; i < positionIndex; i++)
		{
			Current = Current->Next;
		}

		// Set linkages before erasing node

		// If value is last in list
		if (Current->Next != nullptr)
		{
			Current->Next->Previous = Current->Previous;
		}

		Current->Previous->Next = Current->Next;
		delete Current;
	}
	
}


// Search for a value in list, returns index value
int LinkedList::find(short value)
{
	int index = 0;
	LinkedListNode* Current = Head;

	// Go through list
	while (Current != nullptr)
	{
		// Return index of value if match is found
		if (Current->Data == value)
		{
			return index;
		}

		index++;
		Current = Current->Next;
	}
	
	// Return -1 if no match found
	return -1;
}

short& LinkedList::operator[](const int index)
{
	// If index value is out of range
	if (index >= size() || index < 0)
	{
		// return std::numeric_limits<short>::min() gives error of not being a lvalue
		short temp = std::numeric_limits<short>::min();
		return temp;
	}

	LinkedListNode* Current = Head;
	for (int i = 0; i < index; i++)
	{
		Current = Current->Next;
	}
	
	return Current->Data;
}


// << Operator to print out list
std::ostream& operator<<(std::ostream& ostr, const LinkedList& rhs)
{
	LinkedListNode* Current = rhs.Head;
	while (Current != nullptr)
	{
		std::cout << Current->Data << " ";
		Current = Current->Next;
	}

	return ostr;
}
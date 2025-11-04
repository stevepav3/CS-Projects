// STEVE PAV

#pragma once

class LinkedListNode
{
public:

	LinkedListNode(short value, 
				   LinkedListNode* previous = nullptr, 
				   LinkedListNode* next = nullptr);

	short Data;
	LinkedListNode* Previous;
	LinkedListNode* Next;
};
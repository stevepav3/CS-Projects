// STEVE PAV

#pragma once

#include "Operand.h"

class Number : public Operand
{
public:
	Number(double VALUE);
	double value;
};
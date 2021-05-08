#pragma once

typedef struct customListItem_s
{
	void* value;
	struct customListItem_s* next;
} customListItem_t;

typedef struct{
	customListItem_t* first;
	size_t size;
} customList_t;

customList_t* init();
void* getAt(customList_t* list, int idx);
void append(customList_t* list, void* item);
void deleteAt(customList_t* list, int idx);



#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

#include "customList.h"

customList_t* init(){
	customList_t* list = (customList_t*)malloc(sizeof(customList_t));
	list->size = 0;
	list->first = NULL;
	return list;
}
void* getAt(customList_t *list, int idx){
	customListItem_t* current = list->first;
	if (idx < 0 || idx >= list->size)
	{
		return NULL;
	}

	for (int i = 0; i < idx; i++)
	{
		current = current->next;
	}

	return current->value;
}

void append(customList_t *list, void *item){
	customListItem_t *current = list->first;
	customListItem_t *listItem = (customListItem_t *)malloc(sizeof(customListItem_t));

	listItem->value = item;
	listItem->next = NULL;

	if (current != NULL)
	{
		while (current->next != NULL)
		{
			current = current->next;
		}

		current->next = listItem;
	}
	else
	{
		list->first = listItem;
	}

	list->size += 1;
	printf("Listsize:%d\n",list->size);
}
void deleteAt(customList_t *list, int idx){
	customListItem_t *current = list->first;
	customListItem_t *prev = NULL;
	customListItem_t *next = NULL;

	if (idx < 0 || idx >= list->size)
	{
		return;
	}

	if (current != NULL)
	{
		next = current->next;
	}

	for (int i = 0; i < idx; i++)
	{
		prev = current;
		current = current->next;

		if (current != NULL)
		{
			next = current->next;
		}
	}

	if (prev != NULL)
	{
		prev->next = next;
	}

	if (idx == 0)
	{
		list->first = (next == NULL) ? NULL : next;
	}
	free(current->value);
	free(current);

	list->size -= 1;
}
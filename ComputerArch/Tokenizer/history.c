#include <stdio.h>
#include <stdlib.h>
#include "history.h"

/* Initialize the linked list to keep the history. */
List *init_history()
{
  List *history = (List*)malloc(sizeof(List*) * 500);
  return history;
}

/* Add a history item to the end of the list. */
void add_history(List *list, char *str)
{
  Item *newItem = (Item*)malloc(sizeof(Item*) * 500);
  newItem->str = str;

  if (list->root == NULL)
  {
    newItem->id = 1;
    list->root = newItem;
  }
  else
  {
    Item *history = list->root;
    newItem->id = 1;

    while (history->next != NULL)
    {
      newItem->id += 1;
      history = history->next;
    }
    history->next = newItem;
    newItem->id += 1;
  }
}

/* Retrieve the string stored in the node where Item->id == id */
char *get_history(List *list, int id)
{
  Item *history = list->root;
  while (history != NULL)
  {
    if (history->id == id)
      return history->str;

    history = history->next;
  }
  return history->str;
}

/* Print the entire contents of the list. */
void print_history(List *list)
{
  Item *history = list->root;
  while (history->next != NULL)
  {
    printf("%d) %s\n", history -> id, history -> str);
    history = history->next;
  }
  printf("%d) %s\n", history -> id, history -> str);
}

/* Free all Items and the List. */
void free_history(List *list)
{
  while (list->root -> next != NULL)
  {
    Item* history = list->root;
    while (history -> next -> next != NULL ){
      history = history -> next;
    }
    Item* temp = history -> next;
    history -> next = NULL;
    free(history -> str);
    free(temp);
  }
  free(list -> root);
  free(list);
}
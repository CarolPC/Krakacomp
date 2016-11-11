#include <malloc.h>
#include <stdlib.h>
#include <stdio.h>

typedef int boolean;
#define TRUE 1
#define FALSE 0

typedef
  void (*Func)();

typedef
   struct _St_A {
      Func *vt;
      int _A_n;
      } _class_A

_class_A *new_A(void)

void _A_put( _class_Aint pn ) {
}

int _A_get( _class_A ) {
}

Func VTClass_A[] = {
   ( void (*)() ) _put
   ( void (*)() ) _get
   };

_class_A *new_A()
{
   _class_A *t

   if ( (t = malloc(sizeof(_class_A))) != NULL )
      t->vt = _class_A;
   return t;
   }

typedef
      struct _St_No {
         Func *vt;
         _No _No_next;
         _A _No_a;
         } _class_No

_class_No *new_No(void)

void _No_setNext( _class_No_No p_next ) {
}

_No _No_getNext( _class_No ) {
}

void _No_set( _class_No_A pa ) {
}

_A _No_get( _class_No ) {
}

Func VTClass_No[] = {
   ( void (*)() ) _setNext
   ( void (*)() ) _getNext
   ( void (*)() ) _set
   ( void (*)() ) _get
   };

_class_No *new_No()
{
   _class_No *t

   if ( (t = malloc(sizeof(_class_No))) != NULL )
      t->vt = _class_No;
   return t;
   }

typedef
      struct _St_B {
         Func *vt;
         int _B_k;
         _No _B_first;
         } _class_B

_class_B *new_B(void)

int _B_get( _class_B ) {
}

void _B_init( _class_Bint pk ) {
}

void _B_buildList( _class_B ) {
}

void _B_list( _class_B ) {
}

Func VTClass_B[] = {
   ( void (*)() ) _get
   ( void (*)() ) _init
   ( void (*)() ) _buildList
   ( void (*)() ) _list
   };

_class_B *new_B()
{
   _class_B *t

   if ( (t = malloc(sizeof(_class_B))) != NULL )
      t->vt = _class_B;
   return t;
   }

typedef
      struct _St_Program {
         Func *vt;
         } _class_Program

_class_Program *new_Program(void)

void _Program_run( _class_Program ) {
}

Func VTClass_Program[] = {
   ( void (*)() ) _run
   };

_class_Program *new_Program()
{
   _class_Program *t

   if ( (t = malloc(sizeof(_class_Program))) != NULL )
      t->vt = _class_Program;
   return t;
   }

   int main() {      _class_program *program;
      program = new_Program()
      ( ( void (*)(_class_Program *) ) program->vt[0] )(program)
      return 0;
   }

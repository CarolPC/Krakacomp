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

void _A_put( _class_A *thisint pn ) {
    = pn;
   }
int _A_get( _class_A *this ) {
   return ;
   }
Func VTClass_A[] = {
   ( void (*)() ) _A_get,
   ( void (*)() ) _A_put
   };

_class_A *new_A()
{
   _class_A *t;

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

void _No_setNext( _class_No *this_No p_next ) {
    = p_next;
   }
_No _No_getNext( _class_No *this ) {
   return ;
   }
void _No_set( _class_No *this_A pa ) {
    = pa;
   }
_A _No_get( _class_No *this ) {
   return ;
   }
Func VTClass_No[] = {
   ( void (*)() ) _No_get,
   ( void (*)() ) _No_getNext,
   ( void (*)() ) _No_set,
   ( void (*)() ) _No_setNext
   };

_class_No *new_No()
{
   _class_No *t;

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

int _B_get( _class_B *this ) {
   printf();
   return ;
   }
void _B_init( _class_B *thisint pk ) {
    = pk;
   }
void _B_buildList( _class_B *this ) {
   int _i;
   _No _w;
   _No _newNo;
   _A _a;
   i = ;
    = new_No();
   w = ;
   ;
   a = new_A();
   ;
   ;
   while (1) {
      i = i - 1;
      if (i <= 0) {
         break;
      }
      newNo = new No();
      newNo.setNext(this.first);
      a = new A();
      a.put(i);
      newNo.set(a);
      this.first = newNo;
   }
   }
void _B_list( _class_B *this ) {
   _No _w;
   _A _a;
   w = ;
   while (w !=    NULL) {
      a = w.get();
      write (a.get());
      w = w.getNext();
   }
   }
Func VTClass_B[] = {
   ( void (*)() ) _B_buildList,
   ( void (*)() ) _B_get,
   ( void (*)() ) _B_init,
   ( void (*)() ) _B_list,
   ( void (*)() ) _A_put
   };

_class_B *new_B()
{
   _class_B *t;

   if ( (t = malloc(sizeof(_class_B))) != NULL )
      t->vt = _class_B;
   return t;
   }

typedef
   struct _St_Program {
      Func *vt;
      } _class_Program

_class_Program *new_Program(void)

void _Program_run( _class_Program *this ) {
   _B _b;
   b = new_B();
   ;
   ;
   ;
   }
Func VTClass_Program[] = {
   ( void (*)() ) _Program_run
   };

_class_Program *new_Program()
{
   _class_Program *t;

   if ( (t = malloc(sizeof(_class_Program))) != NULL )
      t->vt = _class_Program;
   return t;
   }

   int main() {      _class_program *program;
      program = new_Program()
      ( ( void (*)(_class_Program *) ) program->vt[0] )(program)
      return 0;
   }

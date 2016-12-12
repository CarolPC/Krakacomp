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
      } _class_A;

_class_A *new_A(void);

void _A_print( _class_A *this ) {
   printf("%d   ",0);
}

void _A_accept( _class_A *this,_A _x ) {
   ( (void(*)(_class_A *)) _x->vt[0](_class_A *) this);
}

Func VTClass_A[] = {
   ( void (*)() ) _A_accept,
   ( void (*)() ) _A_print
};

_class_A *new_A()
{
   _class_A *t;

   if ( (t = malloc(sizeof(_class_A))) != NULL )
      t->vt = VTClass_A;
   return t;
}

typedef
   struct _St_B {
      Func *vt;
      } _class_B;

_class_B *new_B(void);

void _B_m( _class_B *this ) {
   ( (void(*)(_class_A *, _A)) vt[1](_class_A *) this,this);
}

Func VTClass_B[] = {
   ( void (*)() ) _A_accept,
   ( void (*)() ) _B_m,
   ( void (*)() ) _A_print
};

_class_B *new_B()
{
   _class_B *t;

   if ( (t = malloc(sizeof(_class_B))) != NULL )
      t->vt = VTClass_B;
   return t;
}

typedef
   struct _St_Program {
      Func *vt;
      } _class_Program;

_class_Program *new_Program(void);

void _Program_run( _class_Program *this ) {
   _B _b;
   _b = new_B();
   ( (void(*)(_class_B *)) _b->vt[2](_class_B *) this);
}

Func VTClass_Program[] = {
   ( void (*)() ) _Program_run
};

_class_Program *new_Program()
{
   _class_Program *t;

   if ( (t = malloc(sizeof(_class_Program))) != NULL )
      t->vt = VTClass_Program;
   return t;
}

int main() {
   _class_program *program;
   program = new_Program()
   ( ( void (*)(_class_Program *) ) program->vt[0] )(program)
   return 0;
}

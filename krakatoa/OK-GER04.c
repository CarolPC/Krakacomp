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

void _A_m( _class_A *this ) {
   int _i;
   boolean _b;
   printf("%d   ",6);
   _i = (1);
   while (_i <= (5)) {
      write (i);
      i = i + 1;
   }
   _b = false;
   while (_b != true) {
      write (6);
      b = !b;
   }
}

Func VTClass_A[] = {
   ( void (*)() ) _A_m
};

_class_A *new_A()
{
   _class_A *t;

   if ( (t = malloc(sizeof(_class_A))) != NULL )
      t->vt = VTClass_A;
   return t;
}

typedef
   struct _St_Program {
      Func *vt;
      } _class_Program;

_class_Program *new_Program(void);

void _Program_run( _class_Program *this ) {
   _class_A* _a;
   printf("%s   ",""\n);
   printf("%s   ","Ok-ger04"\n);
   printf("%s   ","The output should be :"\n);
   printf("%s   ","6 1 2 3 4 5 6"\n);
   _a = new_A();
   ( (void(*)(_class_A *)) _a->vt[0](_a);
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

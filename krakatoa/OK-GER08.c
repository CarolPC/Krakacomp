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

void _A_m1( _class_A *this,int _n ) {
   printf("%d %d   ",1, _n);
}

void _A_m2( _class_A *this,int _n ) {
   printf("%d %d   ",2, _n);
}

void _A_m3( _class_A *this,int _n ) {
   printf("%d %d   ",3, _n);
}

Func VTClass_A[] = {
   ( void (*)() ) _A_m1,
   ( void (*)() ) _A_m2,
   ( void (*)() ) _A_m3
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
   _A _a;
   printf("%s   ",""\n);
   printf("%s   ","Ok-ger08"\n);
   printf("%s   ","The output should be :"\n);
   printf("%s   ","1 1 2 2 3 3"\n);
   _a = new_A();
   ( (void(*)(_class_A *, int)) _a->vt[0](_class_A *) this,1);
   ( (void(*)(_class_A *, int)) _a->vt[1](_class_A *) this,2);
   ( (void(*)(_class_A *, int)) _a->vt[2](_class_A *) this,3);
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

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
      } _class_A;

_class_A *new_A(void);

void _A_set( _class_A *this,int _pn ) {
   this->_n = _pn;
}

int _A_get( _class_A *this ) {
   return this->_n;
}

Func VTClass_A[] = {
   ( void (*)() ) _A_get,
   ( void (*)() ) _A_set
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

void _B_set( _class_B *this,int _pn ) {
   printf("%d   ",_pn);
   ( (void(*)(_class_A *, int)) vt[0](,_pn);
}

Func VTClass_B[] = {
   ( void (*)() ) _A_get,
   ( void (*)() ) _B_set
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

_B _Program_m( _class_Program *this,_A _a ) {
   ( (void(*)(_class_A *, int)) _a->vt[0](_a,0);
   return new_B();
}

_A _Program_p( _class_Program *this,int _i ) {
   if (_i > (0)) {
      return new_A();
   }

   else
      return new_B();

}

void _Program_run( _class_Program *this ) {
   _class_A* _a;
   _class_B* _b;
   _a = new_A();
   _b = new_B();
   _a = _b;
   ( (void(*)(_class_A *, int)) _a->vt[0](_a,0);
   _a = ( (_B(*)(_class_Program *, _A)) this->vt[0](this,_a);
   _b = ( (_B(*)(_class_Program *, _A)) this->vt[0](this,_b);
   _a = ( (_A(*)(_class_Program *, int)) this->vt[1](this,0);
}

Func VTClass_Program[] = {
   ( void (*)() ) _Program_m,
   ( void (*)() ) _Program_p,
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

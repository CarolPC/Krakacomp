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
      int _A_k;
      } _class_A;

_class_A *new_A(void);

void _A_m1( _class_A *this,int _n ) {
   this->_k = (1);
   printf("%d %d   ",this->_k, _n);
}

int _A_getK( _class_A *this ) {
   return this->_k;
}

Func VTClass_A[] = {
   ( void (*)() ) _A_getK,
   ( void (*)() ) _A_m1
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
      int _B_k;
      } _class_B;

_class_B *new_B(void);

void _B_m2( _class_B *this,int _n ) {
   this->_k = (2);
   ( (void(*)(_class_A *, int)) vt[0](,1);
   printf("%d %d   ",this->_k, _n);
}

int _B_getK( _class_B *this ) {
   return this->_k;
}

Func VTClass_B[] = {
   ( void (*)() ) _B_getK,
   ( void (*)() ) _A_m1,
   ( void (*)() ) _B_m2
};

_class_B *new_B()
{
   _class_B *t;

   if ( (t = malloc(sizeof(_class_B))) != NULL )
      t->vt = VTClass_B;
   return t;
}

typedef
   struct _St_C {
      Func *vt;
      } _class_C;

_class_C *new_C(void);

void _C_m3( _class_C *this,int _n ) {
   ( (void(*)(_class_B *, int)) vt[2](,2);
   printf("%d %d   ",3, _n);
}

void _C_m4( _class_C *this,int _n ) {
   ( (void(*)(_class_C *, int)) this->vt[1](this,3);
   printf("%d %d   ",4, _n);
}

Func VTClass_C[] = {
   ( void (*)() ) _B_getK,
   ( void (*)() ) _A_m1,
   ( void (*)() ) _B_m2,
   ( void (*)() ) _C_m3,
   ( void (*)() ) _C_m4
};

_class_C *new_C()
{
   _class_C *t;

   if ( (t = malloc(sizeof(_class_C))) != NULL )
      t->vt = VTClass_C;
   return t;
}

typedef
   struct _St_Program {
      Func *vt;
      } _class_Program;

_class_Program *new_Program(void);

void _Program_run( _class_Program *this ) {
   _class_C* _c;
   printf("%s   ",""\n);
   printf("%s   ","Ok-ger10"\n);
   printf("%s   ","The output should be :"\n);
   printf("%s   ","1 1 2 2 3 3 4 4"\n);
   _c = new_C();
   ( (void(*)(_class_C *, int)) _c->vt[1](_c,4);
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

#include <malloc.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef int boolean;
#define true 1
#define false 0

typedef
  void (*Func)();

typedef
   struct _St_A {
      Func *vt;
      } _class_A;

_class_A *new_A(void);

void _A_m( _class_A *this ) {
   int _i;
   int _j;
   boolean _b;
   _b = false;
   if (_b) {
   }

   else {
   }

   while (_b)
      ;

      while (_b) {
      }

      _i = ((((1) * (4)) + (3)) - ((5) / (2)));
      _b = false;
      if ((!_b && (_i < (0))) || (_i > (10))) {
         _b = true;
         _i = (-(1));
         {
            char __s[512];
            gets(__s);
            sscanf(__s,"%d",&_i);
         }
         {
            char __s[512];
            gets(__s);
            sscanf(__s,"%d",&_j);
         }
         while (_i > (0)) {
            printf("%d", _i);
            _i = (_i - (1));
         }

      }

      else {
         _b = true;
         printf("%d", _i);
      }

      if ((((((_i == (1)) || (_i < (1))) || (_i <= (5))) || (_i > (1))) || (_i >= (1))) || (_i != (3))) {
         _i = (0);
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
   _class_A *_a;
   _a = new_A();
   ( (void(*)(_class_A *)) _a->vt[0])(_a);
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
   _class_Program *program;
   program = new_Program();
   ( ( void (*)(_class_Program *) ) program->vt[0] )(program);
   return 0;
}

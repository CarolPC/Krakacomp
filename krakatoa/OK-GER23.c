#include <malloc.h>
#include <stdlib.h>
#include <stdio.h>

typedef int boolean;
#define TRUE 1
#define FALSE 0

typedef
  void (*Func)();

typedef
   struct _St_Program {
      Func *vt;
      } _class_Program

_class_Program *new_Program(void)

void _Program_run( _class_Program *this ) {
   printf();
   printf();
   int _i, _j, _n;
   i =    0;
   j =    0;
   n =    10;
   int _b;
   b = 0;
   while (0) {
      n = n + 1;
   }
   if ( !( n ==    11 ) ) {
      puts("'do-while' statement with 'false' as expression'");
   }
   n =    10;
   int    0;
   while (j < n) {
      do {
         ++i;
         sum = sum + 1;
      } while (i < n);
      ++j;
   }
   if ( !( sum ==    100 ) ) {
      puts("Nested 'do-while' statement with two indexes");
   }
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

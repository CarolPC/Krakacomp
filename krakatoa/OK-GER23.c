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
      } _class_Program;

_class_Program *new_Program(void);

void _Program_run( _class_Program *this ) {
   printf("%s   ",""\n);
   printf("%s   ","Ok-ger23"\n);
   int _n, _i, _j;
   _i = (0);
   _j = (0);
   _n = (10);
   boolean _b;
   _b = false;
   do {
      n = n + 1;
   } while (false);
   if ( !( _n == (11) ) ) {
      puts("'do-while' statement with 'false' as expression'");
   }
   ;
   _n = (10);
   int 0;
   do {
      do {
         ++i;
         sum = sum + 1;
      } while (i < n);
      ++j;
   } while (j < n);
   if ( !( _sum == (100) ) ) {
      puts("Nested 'do-while' statement with two indexes");
   }
   ;
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

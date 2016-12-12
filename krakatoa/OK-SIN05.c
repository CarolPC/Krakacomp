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
   int _a0;
   int _a1;
   int _a2;
   int _a3;
   int _a4;
   int _a5;
   int _a6;
   int _a7;
   int _a8;
   int _a9;
   int _a10;
   int _a11;
   int _a12;
   int _a13;
   int _a14;
   int _a15;
   int _a16;
   int _a17;
   int _a18;
   int _a19;
   int _a20;
   int _a21;
   int _a22;
   int _a23;
   int _a24;
   int _a25;
   int _a26;
   int _a27;
   int _a28;
   int _a29;
   int _a30;
   int _a31;
   int _a32;
   int _a33;
   int _a34;
   int _a35;
   int _a36;
   int _a37;
   int _a38;
   int _a39;
   int _a40;
   int _a41;
   int _a42;
   int _a43;
   int _a44;
   int _a45;
   int _a46;
   int _a47;
   int _a48;
   int _a49;
   int _a50;
   int _a51;
   int _a52;
   int _a53;
   int _a54;
   int _a55;
   int _a56;
   int _a57;
   int _a58;
   int _a59;
   int _a60;
   int _a61;
   int _a62;
   int _a63;
   int _a64;
   int _a65;
   int _a66;
   int _a67;
   int _a68;
   int _a69;
   int _a70;
   int _a71;
   int _a72;
   int _a73;
   int _a74;
   int _a75;
   int _a76;
   int _a77;
   int _a78;
   int _a79;
   int _a80;
   int _a81;
   int _a82;
   int _a83;
   int _a84;
   int _a85;
   int _a86;
   int _a87;
   int _a88;
   int _a89;
   int _a90;
   int _a91;
   int _a92;
   int _a93;
   int _a94;
   int _a95;
   int _a96;
   int _a97;
   int _a98;
   int _a99;
   int _a100;
   int _a101;
   int _a102;
   int _a103;
   int _a104;
   int _a105;
   int _a106;
   int _a107;
   int _a108;
   int _a109;
   int _a110;
   int _a111;
   int _a112;
   int _a113;
   int _a114;
   int _a115;
   int _a116;
   int _a117;
   int _a118;
   int _a119;
   int _a120;
   int _a121;
   int _a122;
   int _a123;
   int _a124;
   int _a125;
   int _a126;
   int _a127;
   int _a128;
   int _a129;
   int _a130;
   int _a131;
   int _a132;
   int _a133;
   int _a134;
   int _a135;
   int _a136;
   int _a137;
   int _a138;
   int _a139;
   int _a140;
   int _a141;
   int _a142;
   int _a143;
   int _a144;
   int _a145;
   int _a146;
   int _a147;
   int _a148;
   int _a149;
   printf("%d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d   ",_a0, _a1, _a2, _a3, _a4, _a5, _a6, _a7, _a8, _a9, _a10, _a11, _a12, _a13, _a14, _a15, _a16, _a17, _a18, _a19, _a20, _a21, _a22, _a23, _a24, _a25, _a26, _a27, _a28, _a29, _a30, _a31, _a32, _a33, _a34, _a35, _a36, _a37, _a38, _a39, _a40, _a41, _a42, _a43, _a44, _a45, _a46, _a47, _a48, _a49, _a50, _a51, _a52, _a53, _a54, _a55, _a56, _a57, _a58, _a59, _a60, _a61, _a62, _a63, _a64, _a65, _a66, _a67, _a68, _a69, _a70, _a71, _a72, _a73, _a74, _a75, _a76, _a77, _a78, _a79, _a80, _a81, _a82, _a83, _a84, _a85, _a86, _a87, _a88, _a89, _a90, _a91, _a92, _a93, _a94, _a95, _a96, _a97, _a98, _a99, _a100, _a101, _a102, _a103, _a104, _a105, _a106, _a107, _a108, _a109, _a110, _a111, _a112, _a113, _a114, _a115, _a116, _a117, _a118, _a119, _a120, _a121, _a122, _a123, _a124, _a125, _a126, _a127, _a128, _a129, _a130, _a131, _a132, _a133, _a134, _a135, _a136, _a137, _a138, _a139, _a140, _a141, _a142, _a143, _a144, _a145, _a146, _a147, _a148, _a149);
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
   _A _a;
   _a = new_A();
   ( (void(*)(_class_A *)) _a->vt[0](_class_A *) this);
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

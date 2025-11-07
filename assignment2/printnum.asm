section .data
inpm db 'Enter a number: '
inpm_len equ $-inpm
outm db 'The result is: '
outm_len equ $-outm

section .bss
res resb 2
section .text
global _start

_start:

mov eax, 4
mov ebx, 1
mov ecx, inpm
mov edx, inpm_len
int 80h

mov eax, 3
mov ebx, 2
mov ecx, res
mov edx, 20
int 80h

;; Task 2

mov eax, 4
mov ebx, 1
mov ecx, outm
mov edx, outm_len
int 80h

mov eax, 4
mov ebx, 1
mov ecx, res
mov edx, 7
int 80h

mov eax, 4
mov ebx, 1
mov ecx, res
mov edx, 7
int 80h

exit:
mov eax, 1
mov ebx, 0
int 80h
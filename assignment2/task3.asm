section .data
inpm db 'Enter a number: '
inpm_len equ $-inpm
outm db 'The result is: '
outm_len equ $-outm
newline db 10

section .bss
res resb 10

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

mov eax, 0
mov esi, res

checkstart:
movzx edx, byte [esi]
inc esi
cmp edx, '0'
jb checkfinish
cmp edx, '9'
ja checkfinish
sub edx, '0'
add eax, edx
jmp checkstart

checkfinish:
mov ebx, 10
mov ebp, esp

push_loop:
xor edx, edx
div ebx
add edx, '0'
push dx
test eax, eax
jnz push_loop

print_result:
mov eax, 4
mov ebx, 1
mov ecx, outm
mov edx, outm_len
int 80h

pop_loop:
cmp esp, ebp
je done_popping

pop dx
mov [res], dl
mov eax, 4
mov ebx, 1
mov ecx, res
mov edx, 1
int 80h
; loo til stacken er tom
jmp pop_loop

done_popping:

; skriv newline
mov eax, 4
mov ebx, 1
mov ecx, newline
mov edx, 1
int 80h

exit:
mov eax, 1
xor ebx, ebx
int 80h